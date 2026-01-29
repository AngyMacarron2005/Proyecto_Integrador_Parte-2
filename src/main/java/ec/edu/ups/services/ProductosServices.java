package ec.edu.ups.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:src/main/java/ec/edu/ups/services/ProductosServices.java
import ec.edu.ups.dao.ProductosDAO;
import ec.edu.ups.models.ProductosModel;
=======
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.UserCreateDTO;
import ec.edu.ups.services.dto.UserOutputDTO;
import ec.edu.ups.services.dto.UserUpdateDTO;
import ec.edu.ups.services.shared.ApiResponse;
import ec.edu.ups.services.shared.AppError;
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/services/UserHttp.java
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductosServices {

    @Inject
    private ProductosDAO userDAO;

    @GET
<<<<<<< HEAD:src/main/java/ec/edu/ups/services/ProductosServices.java
    public List<ProductosModel> list() {
        return userDAO.list();
=======
    public Response list() {
    	try {
    		List<UserModel> users = userDAO.list();
    		List<UserOutputDTO> resp = new ArrayList<>();
    		for (UserModel user : users) {
    			UserOutputDTO dto = UserOutputDTO.fromModel(user);
    			resp.add(dto);
    		}
    							
            return ApiResponse.success("Correcto", resp, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/services/UserHttp.java
    }

    @GET
    @Path("{id}")
<<<<<<< HEAD:src/main/java/ec/edu/ups/services/ProductosServices.java
    public ProductosModel get(@PathParam("id") Long id) {
        ProductosModel u = userDAO.find(id);
        if (u == null) throw new NotFoundException("User not found: " + id);
        return u;
    }

    @POST
    public Response create(ProductosModel body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        userDAO.create(body);

        URI location = uriInfo.getAbsolutePathBuilder()
                              .path(String.valueOf(body.getId()))
                              .build();
        return Response.created(location).entity(body).build();
=======
    public Response get(@PathParam("id") Long id) {
        try {
        	UserModel u = userDAO.find(id);
        	if (u == null) throw AppError.notFound("User not found: " + id);		
           UserOutputDTO resp = UserOutputDTO.fromModel(u);
           
        	return ApiResponse.success("Correcto", resp, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @POST
    public Response create(UserCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        try {
        	if (body == null) throw AppError.validation("Body required",null);
        	UserModel u = userDAO.create(body);
        	
        	UserOutputDTO resp = UserOutputDTO.fromModel(u);
            return ApiResponse.success("Correcto", resp, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/services/UserHttp.java
    }

    @PUT
    @Path("{id}")
<<<<<<< HEAD:src/main/java/ec/edu/ups/services/ProductosServices.java
    public ProductosModel update(@PathParam("id") Long id, ProductosModel body) {
        if (body == null) throw new BadRequestException("Body required");

        ProductosModel existing = userDAO.find(id);
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
=======
    public Response update(@PathParam("id") Long id, UserUpdateDTO body) {
        try {
        	if (body == null) throw AppError.validation("Body required",null);
        	
        	UserModel existing = userDAO.find(id);
        	if (existing == null) throw AppError.notFound("User not found: " + id);
        	
        	
        	existing.setDisplayName(body.displayName);
        	existing.setRole(body.rol);
        	
        	userDAO.update(existing);
        
        	
            return ApiResponse.success("Correcto",existing, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/services/UserHttp.java
    }
    
}
