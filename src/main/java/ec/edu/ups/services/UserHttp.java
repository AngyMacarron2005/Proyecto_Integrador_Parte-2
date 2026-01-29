package ec.edu.ups.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.UserCreateDTO;
import ec.edu.ups.services.dto.UserOutputDTO;
import ec.edu.ups.services.dto.UserUpdateDTO;
import ec.edu.ups.services.shared.ApiResponse;
import ec.edu.ups.services.shared.AppError;
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
    }

    @GET
    @Path("{id}")
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
    }

    @PUT
    @Path("{id}")
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
    }
    
}
