package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import facades.IPersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO deletePerson(Integer id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Person person = em.find(Person.class, id);
            em.remove(person);
                       
            em.getTransaction().commit();
            
            return new PersonDTO(person);
                
        } finally {
            em.close();
        }
    }

    public PersonDTO editPerson(PersonDTO p) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Person person = em.find(Person.class, p.getId());
            
            if(p.getfName() != null) {
            person.setfName(p.getfName());
            }
            
            if(p.getlName() != null) {
            person.setlName(p.getlName());
            }
            
            if(p.getPhone() != null) {
            person.setPhone(p.getPhone());
            }
            
            person.setLastEdited();
            
            em.getTransaction().commit();
            return new PersonDTO(person);
                
        } finally {
            em.close();
        }
    }
    

    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = getEntityManager();
        Person person = new Person(fName, lName, phone);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return new PersonsDTO(em.createNamedQuery("Person.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    public PersonDTO getPerson(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, id);
            return new PersonDTO(person);
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Person").executeUpdate();
            em.persist(new Person("Kurt", "Wonnegut", "12345678"));
            em.persist(new Person("Peter", "Hansen", "12345678"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
