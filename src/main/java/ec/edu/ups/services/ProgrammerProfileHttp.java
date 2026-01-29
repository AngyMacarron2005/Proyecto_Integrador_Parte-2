package ec.edu.ups.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.ProgrammerProfileDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.ProgrammerOutputDTO;
import ec.edu.ups.services.dto.ProgrammerProfileCreateDTO;
import ec.edu.ups.services.dto.ProgrammerProfileUpdateDTO;
import ec.edu.ups.services.dto.UserOutputDTO;
import ec.edu.ups.services.shared.ApiResponse;
import ec.edu.ups.services.shared.AppError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("programmers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgrammerProfileHttp {

	@Inject
	private ProgrammerProfileDAO programmerDAO;
	@Inject
	private UserDAO userDAO;

	@GET
	public Response list() {
		try {
			List<ProgrammerProfileModel> programmers = programmerDAO.list();
			List<ProgrammerOutputDTO> resp = new ArrayList<>();
			for (ProgrammerProfileModel prog : programmers) {
				ProgrammerOutputDTO dto = ProgrammerOutputDTO.fromModel(prog);
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
        	ProgrammerProfileModel u = programmerDAO.find(id);
        	if (u == null) throw AppError.notFound("User not found: " + id);		
        	ProgrammerOutputDTO resp = ProgrammerOutputDTO.fromModel(u);
           
        	return ApiResponse.success("Correcto", resp, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
	}

	@POST
	public Response create(ProgrammerProfileCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
		 try {
	        	if (body == null) throw AppError.validation("Body required",null);
	        	UserModel user = userDAO.find(body.userId);
	        	ProgrammerProfileModel u = programmerDAO.create(body,user);
	        	
	        	ProgrammerOutputDTO resp = ProgrammerOutputDTO.fromModel(u);
	            return ApiResponse.success("Correcto", resp, 201);
	        } catch (Exception e) {
	            return ApiResponse.error(e);
	        }
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") UUID id, ProgrammerProfileUpdateDTO body) {
		try {
        	if (body == null) throw AppError.validation("Body required",null);
        	
//        	UserModel existing = userDAO.find(id);
//        	if (existing == null) throw AppError.notFound("User not found: " + id);
//        	
//        	
//        	existing.setDisplayName(body.displayName);
//        	existing.setRole(body.rol);
//        	
//        	userDAO.update(existing);
//        
        	
            return ApiResponse.success("Correcto", null, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }

	}
}
