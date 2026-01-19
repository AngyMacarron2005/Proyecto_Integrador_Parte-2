package ec.edu.ups.services;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.PortfolioDAO;
import ec.edu.ups.dao.ProgrammerProfileDAO;
import ec.edu.ups.models.PortfolioModel;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.services.dto.PortfolioCreateDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("portfolios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortafolioHttp {

    @Inject private PortfolioDAO portfolioDAO;
    @Inject private ProgrammerProfileDAO programmerDAO;

    @GET
    public List<PortfolioModel> list() {
        return portfolioDAO.listActive();
    }

    @GET
    @Path("{id}")
    public PortfolioModel get(@PathParam("id") Long id) {
        PortfolioModel p = portfolioDAO.find(id);
        if (p == null || p.isDeleted()) throw new NotFoundException("Portfolio not found: " + id);
        return p;
    }

    @POST
    public Response create(PortfolioCreateDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        if (body.ownerId == null) throw new BadRequestException("ownerId required");

        ProgrammerProfileModel owner = programmerDAO.find(body.ownerId);
        if (owner == null || owner.isDeleted()) throw new NotFoundException("Programmer not found: " + body.ownerId);

        PortfolioModel p = new PortfolioModel();
        p.setOwner(owner);
        p.setTitle(body.title);
        p.setDescription(body.description);
        p.setSections(body.sections);

        portfolioDAO.create(p);

        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(p.getId())).build();
        return Response.created(location).entity(p).build();
    }

    @PUT
    @Path("{id}")
    public PortfolioModel update(@PathParam("id") Long id, PortfolioCreateDTO body) {
        if (body == null) throw new BadRequestException("Body required");

        PortfolioModel p = portfolioDAO.find(id);
        if (p == null || p.isDeleted()) throw new NotFoundException("Portfolio not found: " + id);

        if (body.title != null) p.setTitle(body.title);
        if (body.description != null) p.setDescription(body.description);
        if (body.sections != null) p.setSections(body.sections);

        // owner change (optional)
        if (body.ownerId != null) {
            ProgrammerProfileModel owner = programmerDAO.find(body.ownerId);
            if (owner == null || owner.isDeleted()) throw new NotFoundException("Programmer not found: " + body.ownerId);
            p.setOwner(owner);
        }

        return portfolioDAO.update(p);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean ok = portfolioDAO.softDelete(id);
        if (!ok) throw new NotFoundException("Portfolio not found: " + id);
        return Response.noContent().build();
    }
}
