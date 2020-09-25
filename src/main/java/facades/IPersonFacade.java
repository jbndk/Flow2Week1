package facades;

import dtos.PersonDTO;

/**
 *
 * @author jobe
 */
public interface IPersonFacade {
  public PersonDTO addPerson(String fName, String lName, String phone, String street, String zip, String city);  
  public PersonDTO deletePerson(long id);
  public PersonDTO getPerson(long id); 
  public PersonDTO getAllPersons();  
  public PersonDTO editPerson(PersonDTO p); 
}

