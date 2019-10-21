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

    @GET
    @Path("allZip")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllZips() {
        return GSON.toJson(FACADE.getAllZips());
    }

    @GET
    @Path("pInZip/{zip}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPeopleLivingInZip() {
        return GSON.toJson(FACADE.getAllPeopleLivingInZip());
    }

    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonOutDTO> getPersonInHobby(String hName) {
        List<PersonOutDTO> result = FACADE.getPersonInHobby(hName);
        return result;
    }
    @GET
    @Path("hobbyAmount/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonOutDTO> getAmountofPeopleInHobby(String hName) {
        List<PersonOutDTO> result = FACADE.getAmountofPeopleInHobby(hName);
        return result;
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
    public PersonOutDTO addCompletePerson(PersonInDTO DTO) {
        return FACADE.addCompletePerson(DTO);
    }

}
