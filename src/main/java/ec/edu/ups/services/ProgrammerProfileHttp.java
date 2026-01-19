package ec.edu.ups.services;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.ProgrammerProfileDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.ProgrammerProfileCreateDTO;
import ec.edu.ups.services.dto.ProgrammerProfileUpdateDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("programmers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgrammerProfileHttp {

    @Inject private ProgrammerProfileDAO programmerDAO;
    @Inject private UserDAO userDAO;

    @GET
    public List<ProgrammerProfileModel> list() {
        return programmerDAO.listActive();
    }

    @GET
    @Path("{id}")
    public ProgrammerProfileModel get(@PathParam("id") UUID id) {
        ProgrammerProfileModel p = programmerDAO.find(id);
        if (p == null || p.isDeleted()) throw new NotFoundException("Programmer not found: " + id);
        return p;
    }

    @POST
    public Response create(ProgrammerProfileCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        if (body.userId == null) throw new BadRequestException("userId is required");

        UserModel user = userDAO.find(body.userId);
        if (user == null) throw new NotFoundException("User not found: " + body.userId);

        ProgrammerProfileModel p = new ProgrammerProfileModel();
        p.setUser(user);
        p.setName(body.name);
        p.setSpecialty(body.specialty);
        p.setBio(body.bio);
        p.setAvatarUrl(body.avatarUrl);
        p.setContactLinks(body.contactLinks);

        programmerDAO.create(p);

        URI location = uriInfo.getAbsolutePathBuilder()
                              .path(p.getId().toString())
                              .build();
        return Response.created(location).entity(p).build();
    }

    @PUT
    @Path("{id}")
    public ProgrammerProfileModel update(@PathParam("id") UUID id, ProgrammerProfileUpdateDTO body) {
        if (body == null) throw new BadRequestException("Body required");

        ProgrammerProfileModel p = programmerDAO.find(id);
        if (p == null || p.isDeleted()) throw new NotFoundException("Programmer not found: " + id);

        if (body.name != null) p.setName(body.name);
        if (body.specialty != null) p.setSpecialty(body.specialty);
        if (body.bio != null) p.setBio(body.bio);
        if (body.avatarUrl != null) p.setAvatarUrl(body.avatarUrl);
        if (body.contactLinks != null) p.setContactLinks(body.contactLinks);
        if (body.deleted != null) p.setDeleted(body.deleted);

        return programmerDAO.update(p);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") UUID id) {
        boolean ok = programmerDAO.softDelete(id);
        if (!ok) throw new NotFoundException("Programmer not found: " + id);
        return Response.noContent().build();
    }
}
