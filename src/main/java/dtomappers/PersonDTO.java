package dtomappers;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

public class PersonDTO {

    private long id;
    private String fName;
    private String lName;
    private String email;
    private List<Hobby> hobbies;
    private List<Phone> phone;
    private Address address;
    
    
    public PersonDTO(Person p) {
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.email = p.getEmail();
        this.hobbies = p.getHobbies();
        this.phone = p.getPhone();
        this.address = p.getAddress();
        this.id = p.getId();
    }

    public PersonDTO(String fn, String ln, String email, List<Hobby> hobbies, List<Phone> phone, Address address) {
        this.fName = fn;
        this.lName = ln;
        this.email = email;
        this.hobbies = hobbies;
        this.phone = phone;
        this.address = address;

    }

    public PersonDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setPhone(String email) {
        this.email = email;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
        final PersonDTO other = (PersonDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", hobbies=" + hobbies + ", phone=" + phone + ", address=" + address + '}';
    }


}
