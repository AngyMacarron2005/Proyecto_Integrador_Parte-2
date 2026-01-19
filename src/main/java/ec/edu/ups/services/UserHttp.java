package ec.edu.ups.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.UserModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserHttp {

    @Inject
    private UserDAO userDAO;

    @GET
    public List<UserModel> list() {
        return userDAO.list();
    }

    @GET
    @Path("{id}")
    public UserModel get(@PathParam("id") Long id) {
        UserModel u = userDAO.find(id);
        if (u == null) throw new NotFoundException("User not found: " + id);
        return u;
    }

    @POST
    public Response create(UserModel body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        userDAO.create(body);

        URI location = uriInfo.getAbsolutePathBuilder()
                              .path(String.valueOf(body.getId()))
                              .build();
        return Response.created(location).entity(body).build();
    }

    @PUT
    @Path("{id}")
    public UserModel update(@PathParam("id") Long id, UserModel body) {
        if (body == null) throw new BadRequestException("Body required");

        UserModel existing = userDAO.find(id);
        if (existing == null) throw new NotFoundException("User not found: " + id);

        existing.setFirebaseUid(body.getFirebaseUid());
        existing.setEmail(body.getEmail());
        existing.setDisplayName(body.getDisplayName());
        existing.setPicture(body.getPicture());
        existing.setRole(body.getRole());

        return userDAO.update(existing);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean ok = userDAO.delete(id);
        if (!ok) throw new NotFoundException("User not found: " + id);
        return Response.noContent().build();
    }
}
