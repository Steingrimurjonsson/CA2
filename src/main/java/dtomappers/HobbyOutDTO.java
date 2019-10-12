package dtomappers;

import entities.Hobby;
import entities.Person;

import java.util.List;

public class HobbyOutDTO {

    private long id;
    private String name;
    private String description;
    private List<Person> person;

    public HobbyOutDTO(Hobby h) {
        this.name = h.getName();
        this.description = h.getDescription();

        this.id = h.getId();
    }

    public HobbyOutDTO(HobbyInDTO h) {
        this.name = h.getName();
        this.description = h.getDescription();

        this.id = h.getId();
    }

    public HobbyOutDTO(Long id, String name, String description, List<Person> person) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.person = person;

    }

    public HobbyOutDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        final HobbyOutDTO other = (HobbyOutDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
