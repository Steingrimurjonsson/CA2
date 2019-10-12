package dtomappers;


import entities.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonOutDTO {

    private long id;
    private String fName;
    private String lName;
    private String email;
    private List<HobbyOutDTO> hobbies = new ArrayList();
    private List<PhoneOutDTO> phones = new ArrayList();
    private AddressOutDTO address;

    public PersonOutDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.email = p.getEmail();
        if (p.getHobbies() != null) {
            p.getHobbies().forEach((hobby) -> {
                this.hobbies.add(new HobbyOutDTO(hobby));
            });
        }
        if (p.getPhone() != null) {
            p.getPhone().forEach((ph) -> {
                this.phones.add(new PhoneOutDTO(ph));
            });
        }
        if (p.getAddress() != null) {
            this.address = new AddressOutDTO(p.getAddress());
        }
    }

    public PersonOutDTO(PersonInDTO p) {
        this.id = p.getId();
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.email = p.getEmail();
        if (p.getHobbies() != null) {
            p.getHobbies().forEach((hobby) -> {
                this.hobbies.add(new HobbyOutDTO(hobby));
            });
        }
        if (p.getPhones() != null) {
            p.getPhones().forEach((ph) -> {
                this.phones.add(new PhoneOutDTO(ph));
            });
        }
        if (p.getAddress() != null) {
            this.address = new AddressOutDTO(p.getAddress());
        }
    }

    public PersonOutDTO(String fn, String ln, String email, List<HobbyOutDTO> hobbies, List<PhoneOutDTO> phones, AddressOutDTO address) {

        this.fName = fn;
        this.lName = ln;
        this.email = email;
        if (hobbies != null) {
            hobbies.forEach((hobby) -> {
                this.hobbies.add(hobby);
            });
        }
        if (phones != null) {
            phones.forEach((ph) -> {
                this.phones.add(ph);
            });
        }
        if (address != null) {
            this.address = address;
        }
    }

    public PersonOutDTO() {
    }

    public PersonOutDTO(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public List<HobbyOutDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyOutDTO> hobbies) {
        this.hobbies = hobbies;
    }

    public List<PhoneOutDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneOutDTO> phones) {
        this.phones = phones;
    }

    public AddressOutDTO getAddress() {
        return address;
    }

    public void setAddress(AddressOutDTO address) {
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
        final PersonOutDTO other = (PersonOutDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonOutDTO{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", hobbies=" + hobbies + ", phones=" + phones + ", address=" + address + '}';
    }

}
