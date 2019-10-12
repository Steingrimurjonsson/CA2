package dtomappers;

import entities.Address;
import entities.CityInfo;

import java.util.List;

public class AddressOutDTO {

    private long id;
    private String street;
    private String additionalInfo;
    private CityInfoOutDTO cityInfo;

    public AddressOutDTO(Address a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
        this.cityInfo = new CityInfoOutDTO(a.getCityInfo());

        this.id = a.getId();
    }

    public AddressOutDTO(AddressInDTO a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
        this.cityInfo = new CityInfoOutDTO(a.getCityInfo());

        this.id = a.getId();
    }

    public AddressOutDTO(Long id, String street, String addInfo) {

        this.street = street;
        this.additionalInfo = addInfo;
        this.id = id;

    }

    public AddressOutDTO() {
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

    public CityInfoOutDTO getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoOutDTO cityInfo) {
        this.cityInfo = cityInfo;
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
        final AddressOutDTO other = (AddressOutDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
