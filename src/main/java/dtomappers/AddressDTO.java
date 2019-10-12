package dtomappers;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

public class AddressDTO {

    private long id;
    private String street;
    private String additionalInfo;
  private List<CityInfo> cityInfo;
    private List<Person> person;
    
    
    



  public AddressDTO(Address a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
         this.cityInfo = a.getCityInfo();
  
        this.id = a.getId();
    }
         
    public AddressDTO(String street, String addInfo, List<CityInfo> cityInfo, List<Person> person) {
        this.street = street;
        this.additionalInfo = addInfo;
        this.cityInfo = cityInfo;
        this.person = person;

    }

    public AddressDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<CityInfo> getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(List<CityInfo> cityInfo) {
        this.cityInfo = cityInfo;
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
        final AddressDTO other = (AddressDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddressDTO{" + "id=" + id + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityInfo=" + cityInfo + ", person=" + person + '}';
    }


}
