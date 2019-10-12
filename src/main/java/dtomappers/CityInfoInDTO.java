package dtomappers;

import entities.CityInfo;

import java.util.List;

public class CityInfoInDTO {

    private long id;
    private String zipCode;
    private String city;

    public CityInfoInDTO(CityInfoOutDTO cI) {
        this.zipCode = cI.getZipCode();
        this.city = cI.getCity();

        this.id = cI.getId();
    }

    public CityInfoInDTO(CityInfo cI) {
        this.zipCode = cI.getZipCode();
        this.city = cI.getCity();

        this.id = cI.getId();
    }

    public CityInfoInDTO(Long id, String zC, String C) {
        this.id = id;
        this.zipCode = zC;
        this.city = C;

    }

    public CityInfoInDTO() {
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
        final CityInfoInDTO other = (CityInfoInDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
