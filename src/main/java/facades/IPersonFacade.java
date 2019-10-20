package facades;

import dtomappers.CityInfoInDTO;
import dtomappers.CityInfoOutDTO;
import dtomappers.PersonInDTO;
import dtomappers.PersonOutDTO;
import exceptions.PersonNotFoundException;
import java.util.List;

public interface IPersonFacade {

    public PersonOutDTO addPerson(PersonInDTO DTO);

    public List<Integer> getAllZips();

    public CityInfoOutDTO getCityByZip(String zipCode);

    public PersonOutDTO addCompletePerson(PersonInDTO DTO);

    public PersonOutDTO deletePerson(long id) throws PersonNotFoundException;

    public PersonOutDTO getPerson(long id) throws PersonNotFoundException;

    public List<PersonOutDTO> getAllPersons();

    public PersonOutDTO editPerson(PersonInDTO pID) throws PersonNotFoundException;

}
