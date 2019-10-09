package entities;

import dtomappers.CityInfoDTO;
import dtomappers.PersonDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo"),
    @NamedQuery(name = "CityInfo.getAll", query = "SELECT h FROM CityInfo h"),
    @NamedQuery(name = "CityInfo.getCityInfoByID", query = "SELECT h FROM CityInfo h WHERE h.id = id")})

public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipCode;
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cityinfo", cascade = CascadeType.PERSIST)
    
    
    private List<Address> address;

    public CityInfo() {
    }

    public CityInfo(String zipCode, String city, List<Address> address) {
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
    }

    public CityInfo(CityInfoDTO cI) {

        this.id = cI.getId();
        this.zipCode = cI.getZipCode();
        this.city = cI.getCity();
        this.address = new ArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public String toString() {
        return "CityInfo{" + "id=" + id + ", zipCode=" + zipCode + ", city=" + city + ", addresses=" + address + '}';
    }
}
