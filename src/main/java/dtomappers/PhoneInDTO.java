package dtomappers;


import entities.Phone;
import java.util.List;

public class PhoneInDTO {

    private long id;
    private String number;
    private String description;

    public PhoneInDTO(Phone ph) {
        this.number = ph.getNumber();
        this.description = ph.getDescription();
        this.id = ph.getId();
    }

         
    public PhoneInDTO(Long id, String number, String description) {
     
        this.id = id;
        
        this.number = number;
        this.description = description;
     
    }

    public PhoneInDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        final PhoneInDTO other = (PhoneInDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

 

}
