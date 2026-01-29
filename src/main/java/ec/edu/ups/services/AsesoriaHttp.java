package ec.edu.ups.services;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.AsesoriaDao;
import ec.edu.ups.dao.ProgrammerProfileDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.BookingModel;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.AsesoriaCreateDTO;
import ec.edu.ups.services.dto.AsesoriaOutputDTO;
import ec.edu.ups.services.dto.AsesoriaUpdateDTO;
import ec.edu.ups.services.dto.BookingCreateDTO;
import ec.edu.ups.services.dto.UserOutputDTO;
import ec.edu.ups.services.shared.ApiResponse;
import ec.edu.ups.services.shared.AppError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsesoriaHttp {

	@Inject
	private AsesoriaDao asesoriaDao;
	@Inject
	private ProgrammerProfileDAO programmerDAO;
	@Inject
	private UserDAO userDao;

	@GET
//    public Response list(@QueryParam("requesterId") Long requesterId,@QueryParam("programmerId") UUID programmerId) {
	public Response list() {
		try {
			List<BookingModel> asesories = asesoriaDao.listAll();
			List<AsesoriaOutputDTO> resp = new ArrayList<>();
			for (BookingModel ass : asesories) {
				AsesoriaOutputDTO dto = AsesoriaOutputDTO.fromModel(ass);
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
			BookingModel u = asesoriaDao.find(id);
			if (u == null)
				throw AppError.notFound("User not found: " + id);
			AsesoriaOutputDTO resp = AsesoriaOutputDTO.fromModel(u);
			return ApiResponse.success("Correcto", resp, 201);
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}

	@POST
	public Response create(AsesoriaCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
		try {
			if (body == null)
				throw AppError.validation("Body required", null);
			UserModel requester = userDao.find(body.userId);
			ProgrammerProfileModel programmer = programmerDAO.find(body.programmerId);
			if (requester == null || programmer == null) {
				throw AppError.validation("User or Programmer not found", null);
			}

			BookingModel u = asesoriaDao.create(body, programmer, requester);

			AsesoriaOutputDTO resp = AsesoriaOutputDTO.fromModel(u);
			return ApiResponse.success("Correcto", resp, 201);
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, AsesoriaUpdateDTO body) {
		try {
if (body == null) throw AppError.validation("Body required",null);
        	
        	BookingModel existing = asesoriaDao.find(id);
        	if (existing == null) throw AppError.notFound("Asesoria not found: " + id);
        	
        	
        	existing.setStatus(body.status);
        	existing.setResponseMessage(body.responseMessage);
        	
        	asesoriaDao.update(existing);
        
        	
            return ApiResponse.success("Correcto",existing, 201);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
	}


}
