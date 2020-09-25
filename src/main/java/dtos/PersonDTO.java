package dtos;

import entities.Person;

/**
 *
 * @author Jonas
 */

public class PersonDTO {
    private Integer id;
    private String fName;
    private String lName;
    private String phone;
    
    public PersonDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }
    
    public PersonDTO(String fName,String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;        
    }
    
    public PersonDTO() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
      
}
