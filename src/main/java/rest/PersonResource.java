package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        PersonsDTO persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerson(@PathParam("id") Integer id ) throws PersonNotFoundException   {
        PersonDTO personDTO = FACADE.getPerson(id);
        return GSON.toJson(personDTO);
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person) {
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newPerson = FACADE.addPerson(personDTO.getfName(), personDTO.getlName(), personDTO.getPhone());
        return GSON.toJson(newPerson);
    }
    
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editPerson(@PathParam("id") Integer id,  String person) {
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO newPerson = FACADE.editPerson(personDTO);
        return GSON.toJson(newPerson);
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") Integer id) throws PersonNotFoundException {
        PersonDTO personDTO = FACADE.deletePerson(id);
        return GSON.toJson(personDTO);
    }
    
    
}
    
    
    /*@GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }
    }
    */



    /*
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    */
