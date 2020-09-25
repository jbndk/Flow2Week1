package facades;

import exceptions.PersonNotFoundException;
import dtos.PersonDTO;
import dtos.PersonsDTO;

/**
 *
 * @author Jonas
 */

public interface IPersonFacade {
  public PersonDTO addPerson(String fName, String lName, String phone);  
  public PersonDTO deletePerson(Integer id) throws PersonNotFoundException;
  public PersonDTO getPerson(Integer id) throws PersonNotFoundException; 
  public PersonsDTO getAllPersons();  
  public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException ;  
}