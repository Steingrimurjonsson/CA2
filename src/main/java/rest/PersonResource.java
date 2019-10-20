package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtomappers.CityInfoOutDTO;
import dtomappers.PersonInDTO;
import dtomappers.PersonOutDTO;
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
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getFacadePerson(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String isUp() {
        return "{\"status\":\"OK\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String p) {
        PersonInDTO newP = GSON.fromJson(p, PersonInDTO.class);
        PersonOutDTO cP = FACADE.addPerson(newP);
        return Response.ok(cP).build();
    }

    @POST
    @Path("/addF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonOutDTO addCompletePerson(PersonInDTO person) {
        return FACADE.addCompletePerson(person);
    }

    @GET
    @Path("zip/{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    public CityInfoOutDTO getCityByZip(String zipCode) {
        CityInfoOutDTO result = FACADE.getCityByZip(zipCode);
        return result;
    }

    @GET
    @Path("zipAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getAllZipCodes() {
        return FACADE.getAllZips();
    }

}
