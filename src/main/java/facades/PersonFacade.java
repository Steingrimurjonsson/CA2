package facades;

import dtomappers.HobbyInDTO;
import dtomappers.PersonInDTO;
import dtomappers.PersonOutDTO;
import dtomappers.PhoneInDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadePerson(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    // public long getPersonCount() {
    //    EntityManager em = emf.createEntityManager();
    //    try {
    //         long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
    //        return renameMeCount;
    //     } finally {
    //         em.close();
    //     }
//
    // }
    @Override
    public List<PersonOutDTO> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            List<Person> ps = em.createNamedQuery("Person.All").getResultList();
            List<PersonOutDTO> allPersons = new ArrayList();
            ps.forEach((p) -> {
                allPersons.add(new PersonOutDTO(p));
            });
            return allPersons;
        } finally {
            em.close();
        }
    }

    @Override
    public PersonOutDTO addPerson(PersonInDTO DTO) {
        EntityManager em = getEntityManager();
        Person person = new Person(DTO.getfName(), DTO.getlName(), DTO.getEmail(), null);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            PersonOutDTO ADDED = new PersonOutDTO(person);
            return ADDED;
        } finally {
            em.close();
        }
    }

    @Override
    public PersonOutDTO addCompletePerson(PersonInDTO DTO) {
        EntityManager em = getEntityManager();
        Person person = new Person(DTO.getfName(), DTO.getlName(), DTO.getEmail());
     
        try {
            em.getTransaction().begin();
            
          DTO.getPhones().forEach((p) -> {
            Phone phone = new Phone(p);
            phone.setPerson(person);
            person.addPhone(phone);
        });
            
            DTO.getHobbies().forEach((hDTO) -> {
                String description = hDTO.getDescription();
                String name = hDTO.getName();
                Hobby hob = new Hobby(name, description);
                Hobby h = em.createNamedQuery("SELECT h FROM Hobby h WHERE h.name = :name AND h.description = :description", Hobby.class).setParameter("name", name).setParameter("description", description).getSingleResult();
                if (h == null) {
                    hob = new Hobby(name, description);
                }
                hob = em.merge(hob);
                person.addHobby(hob);
            });

            String street = DTO.getAddress().getStreet();
            String additionalInfo = DTO.getAddress().getAdditionalInfo();
            Address address = new Address(street, additionalInfo);

            String zipCode = DTO.getAddress().getCityInfo().getZipCode();
            String city = DTO.getAddress().getCityInfo().getCity();
            CityInfo cityInfo = new CityInfo(zipCode, city);

            CityInfo c = em.createNamedQuery("SELECT c FROM CityInfo c WHERE c.zipCode = :zipCode", CityInfo.class).setParameter("zipCode", zipCode).setParameter("city", city).getSingleResult();
            if (c != null) {
                address.setCityInfo(cityInfo);
                address = em.merge(address);
                address.setPerson(person);
                person.setAddress(address);
            }
            em.persist(person);
            em.getTransaction().commit();
            PersonOutDTO ADDED = new PersonOutDTO(person);
            return ADDED;
        } finally {
            em.close();
        }
    }

    @Override
    public PersonOutDTO deletePerson(long id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new PersonNotFoundException(String.format("Person with id: (%d) not found", id));
        }
        try {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return new PersonOutDTO(person);
        } finally {
            em.close();
        }

    }

    @Override
    public PersonOutDTO getPerson(long id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new PersonNotFoundException(String.format("Person with id: (%d) not found", id));
        }
        try {
            person = em.find(Person.class, id);
            return new PersonOutDTO(person);
        } finally {
            em.close();
        }

    }

    @Override
    public PersonOutDTO editPerson(PersonInDTO DTO) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person pID = em.find(Person.class, DTO.getId());
            if (pID == null) {
                throw new PersonNotFoundException(String.format("Person with id: (%d) not found", pID.getId()));
            }
            if (DTO.getfName() != null) {
                pID.setfName(DTO.getfName());
            }
            if (DTO.getlName() != null) {
                pID.setlName(DTO.getlName());
            }
            if (DTO.getEmail() != null) {
                pID.setEmail(DTO.getEmail());
            }

            Address a;
            CityInfo c;
            List<HobbyInDTO> hobbies = DTO.getHobbies();
            List<PhoneInDTO> phones = DTO.getPhones();

            if (DTO.getAddress() != null) {
                a = em.find(Address.class, DTO.getAddress().getId());
                if (a == null) {
                    a = new Address(DTO.getAddress());
                    em.persist(a);
                } else {
                    a.setAdditionalInfo(DTO.getAddress().getAdditionalInfo());
                    a.setStreet(DTO.getAddress().getStreet());
                    a = em.merge(a);
                }
                if (DTO.getAddress().getCityInfo() != null) {
                    c = em.find(CityInfo.class, DTO.getAddress().getCityInfo().getId());
                    if (c == null) {
                        c = new CityInfo(DTO.getAddress().getCityInfo());
                        em.persist(c);
                    } else {
                        c.setCity(DTO.getAddress().getCityInfo().getCity());
                        c.setZipCode(DTO.getAddress().getCityInfo().getZipCode());
                        c = em.merge(c);
                    }
                    a.setCityInfo(c);
                }
                pID.setAddress(a);
            }
            if (hobbies != null) {
                hobbies.forEach((hobbyDTO) -> {
                    Hobby h;
                    h = em.find(Hobby.class, hobbyDTO.getId());
                    if (h != null) {
                        h.setDescription(hobbyDTO.getDescription());
                        h.setName(hobbyDTO.getName());
                        em.merge(h);
                    } else {
                        h = new Hobby(hobbyDTO);
                        em.persist(h);
                    }
                    if (!pID.getHobbies().contains(h)) {
                        pID.addHobby(h);
                    }
                });
            }
            if (phones != null) {
                phones.forEach((pDTO) -> {
                    Phone ph;
                    ph = em.find(Phone.class, pDTO.getId());
                    if (ph != null) {
                        ph.setDescription(pDTO.getDescription());
                        ph.setNumber(pDTO.getNumber());
                        em.merge(ph);
                    } else {
                        ph = new Phone(pDTO);
                        em.persist(ph);
                    }
                    if (!pID.getPhone().contains(ph)) {
                        pID.addPhone(ph);
                    }
                });
            }
            Person ePerson = em.merge(pID);
            em.getTransaction().commit();
            return new PersonOutDTO(ePerson);
        } finally {
            em.close();
        }
    }

}
