package dtomappers;

import entities.Address;
import java.util.List;

public class AddressInDTO {

    private long id;
    private String street;
    private String additionalInfo;
    private CityInfoInDTO cityInfo;

    public AddressInDTO(Address a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
        this.cityInfo = new CityInfoInDTO(a.getCityInfo());

        this.id = a.getId();
    }

    public AddressInDTO(Long id, String street, String addInfo) {
        this.id = id;
        this.street = street;
        this.additionalInfo = addInfo;

    }

    public AddressInDTO() {
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

    public CityInfoInDTO getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoInDTO cityInfo) {
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
        final AddressInDTO other = (AddressInDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddressInDTO{" + "id=" + id + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityInfo=" + cityInfo + '}';
    }



}
