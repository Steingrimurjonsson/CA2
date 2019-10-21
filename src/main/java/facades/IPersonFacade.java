package facades;

import dtomappers.CityInfoInDTO;
import dtomappers.CityInfoOutDTO;
import dtomappers.HobbyOutDTO;
import dtomappers.PersonInDTO;
import dtomappers.PersonOutDTO;
import exceptions.PersonNotFoundException;
import java.util.List;

public interface IPersonFacade {

    public PersonOutDTO addPerson(PersonInDTO DTO);

    public List<CityInfoOutDTO> getAllZips();

    public List<CityInfoOutDTO> getAllPeopleLivingInZip();

    public PersonOutDTO addCompletePerson(PersonInDTO DTO);

    public PersonOutDTO deletePerson(long id) throws PersonNotFoundException;

    public PersonOutDTO getPerson(long id) throws PersonNotFoundException;

    public List<PersonOutDTO> getAllPersons();

    public List<PersonOutDTO> getPersonInHobby(String hName);

    public List<PersonOutDTO> getAmountofPeopleInHobby(String hName);
}
