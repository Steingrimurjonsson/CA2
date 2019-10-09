package entities;

import dtomappers.AddressDTO;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address"),
    @NamedQuery(name = "Address.getAll", query = "SELECT h FROM Address h"),
    @NamedQuery(name = "Address.getAddressByID", query = "SELECT h FROM Address h WHERE h.id = id")})

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String additionalInfo;
    
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "personID")
    private List<Person> persons = new ArrayList();
    private List<CityInfo> cityInfo = new ArrayList();
    public Address() {
    }

    public Address(String street, String additionalInfo, List<CityInfo> cityInfo) {
     
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
        
    }
    
    public Address(AddressDTO address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
          this.cityInfo = address.getCityInfo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<CityInfo> getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(List<CityInfo> cityInfo) {
        this.cityInfo = cityInfo;
    }


  @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityInfo=" + cityInfo + ", person=" + persons + '}';
    }
    
}
