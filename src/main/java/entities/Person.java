package entities;

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
    @NamedQuery(name = "Person.getAll", query = "SELECT h FROM Person h"),
    @NamedQuery(name = "Person.getPersonByID", query = "SELECT h FROM Person h WHERE h.id = id")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hobbyID")

    private List<Hobby> hobbies = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "phoneID")

    private List<Phone> phone = new ArrayList();

    @ManyToOne
    private Address address;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public Person(String firstName, String lastName, String email, List<Hobby> hobbies, List<Phone> phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hobbies = hobbies;
        this.phone = phone;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", hobbies=" + hobbies + ", phone=" + phone + ", address=" + address + '}';
    }

}
