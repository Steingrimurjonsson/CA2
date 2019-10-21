package facades;

import dtomappers.CityInfoOutDTO;
import dtomappers.HobbyInDTO;
import dtomappers.HobbyOutDTO;
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
    public List<CityInfoOutDTO> getAllZips() {
        EntityManager em = getEntityManager();
        try {
            List<CityInfo> zip = em.createNamedQuery("CityInfo.AllZip").getResultList();
            List<CityInfoOutDTO> allZips = new ArrayList();
            zip.forEach((z) -> {
                allZips.add(new CityInfoOutDTO(z));
            });
            return allZips;
        } finally {
            em.close();
        }
    }
      @Override
    public List<CityInfoOutDTO> getAllPeopleLivingInZip() {
        EntityManager em = getEntityManager();
        try {
            List<CityInfo> zip = em.createNamedQuery("CityInfo.peopleInZip").getResultList();
            List<CityInfoOutDTO> pInZ = new ArrayList();
            zip.forEach((z) -> {
                pInZ.add(new CityInfoOutDTO(z));
            });
            return pInZ;
        } finally {
            em.close();
        }
    }
    @Override
    public List<PersonOutDTO> getPersonInHobby(String name) {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getPeopleInHobby").setParameter("name", name).getResultList();
          
            List<PersonOutDTO> peopleinHobby = new ArrayList<>();
            persons.forEach((person) -> {
                peopleinHobby.add(new PersonOutDTO(person));
            });
            return peopleinHobby;
      } finally {
            em.close();
        }
    }
      @Override
    public List<PersonOutDTO> getAmountofPeopleInHobby(String name) {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.AmountOfPeopleInHobby").setParameter("name", name).getResultList();
          
            List<PersonOutDTO> peopleinHobby = new ArrayList<>();
            persons.forEach((person) -> {
                peopleinHobby.add(new PersonOutDTO(person));
            });
            return peopleinHobby;
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

}
