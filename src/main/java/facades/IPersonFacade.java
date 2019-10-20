package facades;

import dtomappers.CityInfoInDTO;
import dtomappers.PersonInDTO;
import dtomappers.PersonOutDTO;
import exceptions.PersonNotFoundException;
import java.util.List;

public interface IPersonFacade {

    public PersonOutDTO addPerson(PersonInDTO DTO);
        
    public PersonOutDTO addCompletePerson(PersonInDTO DTO);

    public PersonOutDTO deletePerson(long id) throws PersonNotFoundException;

    public PersonOutDTO getPerson(long id) throws PersonNotFoundException;

    public List<PersonOutDTO> getAllPersons();

    public PersonOutDTO editPerson(PersonInDTO pID) throws PersonNotFoundException;


 
}
