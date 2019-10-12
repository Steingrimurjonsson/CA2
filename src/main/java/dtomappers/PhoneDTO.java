package dtomappers;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

public class PhoneDTO {

    private long id;
    private String number;
    private String description;
    private List<Person> person;

    public PhoneDTO(Phone ph) {
        this.number = ph.getNumber();
        this.description = ph.getDescription();
        this.id = ph.getId();
    }

         
    public PhoneDTO(String number, String description, List<Person> person) {
        this.number = number;
        this.description = description;
      
        this.person = person;

    }

    public PhoneDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhoneDTO other = (PhoneDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" + "id=" + id + ", number=" + number + ", description=" + description + ", person=" + person + '}';
    }


}
