package ec.edu.ups.services;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.BookingDAO;
import ec.edu.ups.dao.ProgrammerProfileDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.BookingModel;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.BookingCreateDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingHttp {

    @Inject private BookingDAO bookingDAO;
    @Inject private UserDAO userDAO;
    @Inject private ProgrammerProfileDAO programmerDAO;

    @GET
    public List<BookingModel> list(@QueryParam("requesterId") Long requesterId,
                                   @QueryParam("programmerId") UUID programmerId) {
        if (requesterId != null) return bookingDAO.listByRequester(requesterId);
        if (programmerId != null) return bookingDAO.listByProgrammer(programmerId);
        return bookingDAO.listAll();
    }

    @GET
    @Path("{id}")
    public BookingModel get(@PathParam("id") UUID id) {
        BookingModel b = bookingDAO.find(id);
        if (b == null) throw new NotFoundException("Booking not found: " + id);
        return b;
    }

    @POST
    public Response create(BookingCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        if (body.requesterId == null) throw new BadRequestException("requesterId required");
        if (body.programmerId == null) throw new BadRequestException("programmerId required");
        if (body.date == null) throw new BadRequestException("date required (yyyy-MM-dd)");
        if (body.weekday == null) throw new BadRequestException("weekday required");
        if (body.hour < 0 || body.hour > 23) throw new BadRequestException("hour must be 0..23");
        if (body.durationMin <= 0) throw new BadRequestException("durationMin must be > 0");

        UserModel requester = userDAO.find(body.requesterId);
        if (requester == null) throw new NotFoundException("User not found: " + body.requesterId);

        ProgrammerProfileModel programmer = programmerDAO.find(body.programmerId);
        if (programmer == null || programmer.isDeleted()) throw new NotFoundException("Programmer not found: " + body.programmerId);

        BookingModel b = new BookingModel();
        b.setRequester(requester);
        b.setProgrammer(programmer);
        b.setDate(LocalDate.parse(body.date));
        b.setWeekday(body.weekday);
        b.setHour(body.hour);
        b.setDurationMin(body.durationMin);
        b.setComment(body.comment);

        if (body.status != null) b.setStatus(body.status);

        bookingDAO.create(b);

        URI location = uriInfo.getAbsolutePathBuilder().path(b.getId().toString()).build();
        return Response.created(location).entity(b).build();
    }

    @PUT
    @Path("{id}")
    public BookingModel update(@PathParam("id") UUID id, BookingCreateDTO body) {
        if (body == null) throw new BadRequestException("Body required");

        BookingModel b = bookingDAO.find(id);
        if (b == null) throw new NotFoundException("Booking not found: " + id);

        if (body.date != null) b.setDate(LocalDate.parse(body.date));
        if (body.weekday != null) b.setWeekday(body.weekday);
        if (body.hour >= 0 && body.hour <= 23) b.setHour(body.hour);
        if (body.durationMin > 0) b.setDurationMin(body.durationMin);
        if (body.comment != null) b.setComment(body.comment);
        if (body.status != null) b.setStatus(body.status);

        // Optional: allow changing requester/programmer
        if (body.requesterId != null) {
            UserModel requester = userDAO.find(body.requesterId);
            if (requester == null) throw new NotFoundException("User not found: " + body.requesterId);
            b.setRequester(requester);
        }
        if (body.programmerId != null) {
            ProgrammerProfileModel programmer = programmerDAO.find(body.programmerId);
            if (programmer == null || programmer.isDeleted()) throw new NotFoundException("Programmer not found: " + body.programmerId);
            b.setProgrammer(programmer);
        }

        return bookingDAO.update(b);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") UUID id) {
        boolean ok = bookingDAO.delete(id);
        if (!ok) throw new NotFoundException("Booking not found: " + id);
        return Response.noContent().build();
    }
}
