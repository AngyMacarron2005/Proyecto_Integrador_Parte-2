package ec.edu.ups.services;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.ProgrammerAvailabilityDAO;
import ec.edu.ups.dao.ProgrammerProfileDAO;
import ec.edu.ups.models.ProgrammerAvailabilityModel;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.services.dto.AvailabilityCreateDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("availabilities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgrammerAvailabilityHttp {

    @Inject private ProgrammerAvailabilityDAO availabilityDAO;
    @Inject private ProgrammerProfileDAO programmerDAO;

    @GET
    public List<ProgrammerAvailabilityModel> list(@QueryParam("programmerId") UUID programmerId) {
        if (programmerId != null) {
            return availabilityDAO.listByProgrammer(programmerId);
        }
        return availabilityDAO.listAll();
    }

    @GET
    @Path("{id}")
    public ProgrammerAvailabilityModel get(@PathParam("id") UUID id) {
        ProgrammerAvailabilityModel a = availabilityDAO.find(id);
        if (a == null) throw new NotFoundException("Availability not found: " + id);
        return a;
    }

    @POST
    public Response create(AvailabilityCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        if (body.programmerId == null) throw new BadRequestException("programmerId required");
        if (body.day == null) throw new BadRequestException("day required");
        if (body.startMinutes < 0 || body.endMinutes < 0 || body.endMinutes <= body.startMinutes) {
            throw new BadRequestException("Invalid time range");
        }

        ProgrammerProfileModel programmer = programmerDAO.find(body.programmerId);
        if (programmer == null || programmer.isDeleted()) {
            throw new NotFoundException("Programmer not found: " + body.programmerId);
        }

        ProgrammerAvailabilityModel a = new ProgrammerAvailabilityModel();
        a.setProgrammer(programmer);
        a.setDay(body.day);
        a.setStartMinutes(body.startMinutes);
        a.setEndMinutes(body.endMinutes);

        availabilityDAO.create(a);

        URI location = uriInfo.getAbsolutePathBuilder().path(a.getId().toString()).build();
        return Response.created(location).entity(a).build();
    }

    @PUT
    @Path("{id}")
    public ProgrammerAvailabilityModel update(@PathParam("id") UUID id, AvailabilityCreateDTO body) {
        if (body == null) throw new BadRequestException("Body required");

        ProgrammerAvailabilityModel a = availabilityDAO.find(id);
        if (a == null) throw new NotFoundException("Availability not found: " + id);

        if (body.day != null) a.setDay(body.day);
        if (body.startMinutes >= 0) a.setStartMinutes(body.startMinutes);
        if (body.endMinutes >= 0) a.setEndMinutes(body.endMinutes);

        if (a.getEndMinutes() <= a.getStartMinutes()) {
            throw new BadRequestException("Invalid time range");
        }

        return availabilityDAO.update(a);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") UUID id) {
        boolean ok = availabilityDAO.delete(id);
        if (!ok) throw new NotFoundException("Availability not found: " + id);
        return Response.noContent().build();
    }
}
