package entities;

import dtomappers.PersonInDTO;
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
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person"),
    @NamedQuery(name = "Person.All", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.getPeopleInHobby", query = "SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :name"),
    @NamedQuery(name = "Person.AmountOfPeopleInHobby", query = "SELECT count(p) FROM Person p JOIN p.hobbies h WHERE h.name = :name")
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fName;
    private String lName;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hobbiesID")

    private List<Hobby> hobbies = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "phoneID")

    private List<Phone> phone = new ArrayList();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressID")
    private Address address;

    public Person() {
    }

    public Person(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = new ArrayList();
    }

    public Person(String fName, String lName, String email, List<Hobby> hobbies) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = new ArrayList();
        this.hobbies = hobbies;
    }

    public Person(PersonInDTO p) {
        this.email = p.getEmail();
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.phone = new ArrayList();
    }

    public Person(String fName, String lName, String email, List<Hobby> hobbies, List<Phone> phone, Address address) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.hobbies = hobbies;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public void addPhone(Phone phone) {
        this.phone.add(phone);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Person other = (Person) obj;

        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /*@ElementCollection
    @CollectionTable(
            name = "hobbies"
    )
    @Column(name = "Hobby")
    private List<String> hobbies = new ArrayList();

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    public String getHobbies() {
        return String.join(",", hobbies);
    }*/
    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", hobbies=" + hobbies + ", phone=" + phone + ", address=" + address + '}';
    }

}
