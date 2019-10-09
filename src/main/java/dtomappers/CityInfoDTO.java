package dtomappers;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

public class CityInfoDTO {

    private long id;
    private String zipCode;
    private String city;

    private List<Address> address;

    
    public CityInfoDTO(CityInfo cI) {
        this.zipCode = cI.getZipCode();
        this.city = cI.getCity();
 
        this.id = cI.getId();
    }

         
    public CityInfoDTO(String zC, String C, List<Address> address) {
        this.zipCode = zC;
        this.city = C;
        this.address = address;

    }

    public CityInfoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
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
        final CityInfoDTO other = (CityInfoDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
