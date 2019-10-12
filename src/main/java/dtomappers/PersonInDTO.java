package dtomappers;


import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonInDTO {

    private long id;
    private String fName;
    private String lName;
    private String email;
    private List<HobbyInDTO> hobbies = new ArrayList();
    private List<PhoneInDTO> phones = new ArrayList();
    private AddressInDTO address;

    public PersonInDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.email = p.getEmail();
        if (p.getHobbies() != null) {
            p.getHobbies().forEach((hobby) -> {
                this.hobbies.add(new HobbyInDTO(hobby));
            });
        }
        if (p.getPhone() != null) {
            p.getPhone().forEach((ph) -> {
                this.phones.add(new PhoneInDTO(ph));
            });
        }
        if (p.getAddress() != null) {
            this.address = new AddressInDTO(p.getAddress());
        }
    }

    public PersonInDTO(String fn, String ln, String email, List<HobbyInDTO> hobbies, List<PhoneInDTO> phones, AddressInDTO address) {

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


    public PersonInDTO() {
    }

    public PersonInDTO(String fName, String lName, String email) {
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

    public List<HobbyInDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyInDTO> hobbies) {
        this.hobbies = hobbies;
    }

    public List<PhoneInDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneInDTO> phones) {
        this.phones = phones;
    }

    public AddressInDTO getAddress() {
        return address;
    }

    public void setAddress(AddressInDTO address) {
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
        final PersonInDTO other = (PersonInDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonInDTO{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", hobbies=" + hobbies + ", phones=" + phones + ", address=" + address + '}';
    }


}
