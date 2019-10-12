package entities;

import dtomappers.HobbyDTO;
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
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE from Hobby"),
    @NamedQuery(name = "Hobby.getAll", query = "SELECT h FROM Hobby h"),
    @NamedQuery(name = "Hobby.getHobbyByID", query = "SELECT h FROM Hobby h WHERE h.id = id")})

public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    
    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "personID")
    private List<Person> persons = new ArrayList();

    public Hobby() {
    }
    
    public Hobby(HobbyDTO h) {
        this.id = h.getId();
        this.name = h.getName();
        this.description = h.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
   @Override
    public String toString() {
        return "Hobby{" + "id=" + id + ", name=" + name + ", description=" + description + ", persons=" + persons + '}';
    }
    
}
