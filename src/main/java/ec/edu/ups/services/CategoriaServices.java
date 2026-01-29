package ec.edu.ups.services;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.dao.PortfolioDAO;
import ec.edu.ups.dao.CategoriaDAO;
import ec.edu.ups.models.PortfolioModel;
import ec.edu.ups.models.CategoriaModel;
import ec.edu.ups.services.dto.CategoriaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaServices {

    @Inject private CategoriaDAO projectDAO;
    @Inject private PortfolioDAO portfolioDAO;

    @GET
    public List<CategoriaModel> list(@QueryParam("portfolioId") Long portfolioId) {
        if (portfolioId != null) return projectDAO.listByPortfolio(portfolioId);
        return projectDAO.listActive();
    }

    @GET
    @Path("{id}")
    public CategoriaModel get(@PathParam("id") UUID id) {
        CategoriaModel p = projectDAO.find(id);
        if (p == null || p.isDeleted()) throw new NotFoundException("Project not found: " + id);
        return p;
    }

    @POST
    public Response create(CategoriaDTO body, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        if (body == null) throw new BadRequestException("Body required");
        if (body.portfolioId == null) throw new BadRequestException("portfolioId required");

        PortfolioModel portfolio = portfolioDAO.find(body.portfolioId);
        if (portfolio == null || portfolio.isDeleted()) throw new NotFoundException("Portfolio not found: " + body.portfolioId);

        CategoriaModel p = new CategoriaModel();
        p.setPortfolio(portfolio);
        p.setTitle(body.title);
        p.setDescription(body.description);
        p.setSection(body.section);
        p.setParticipation(body.participation);
        p.setTechnologies(body.technologies);
        p.setRepositoryUrl(body.repositoryUrl);
        p.setLiveDemoUrl(body.liveDemoUrl);
        p.setImages(body.images);

        projectDAO.create(p);

        URI location = uriInfo.getAbsolutePathBuilder().path(p.getId().toString()).build();
        return Response.created(location).entity(p).build();
    }

    @PUT
    @Path("{id}")
    public CategoriaModel update(@PathParam("id") UUID id, CategoriaDTO body) {
        if (body == null) throw new BadRequestException("Body required");

        CategoriaModel p = projectDAO.find(id);
        if (p == null || p.isDeleted()) throw new NotFoundException("Project not found: " + id);

        if (body.portfolioId != null) {
            PortfolioModel portfolio = portfolioDAO.find(body.portfolioId);
            if (portfolio == null || portfolio.isDeleted()) throw new NotFoundException("Portfolio not found: " + body.portfolioId);
            p.setPortfolio(portfolio);
        }

        if (body.title != null) p.setTitle(body.title);
        if (body.description != null) p.setDescription(body.description);
        if (body.section != null) p.setSection(body.section);
        if (body.participation != null) p.setParticipation(body.participation);
        if (body.technologies != null) p.setTechnologies(body.technologies);
        if (body.repositoryUrl != null) p.setRepositoryUrl(body.repositoryUrl);
        if (body.liveDemoUrl != null) p.setLiveDemoUrl(body.liveDemoUrl);
        if (body.images != null) p.setImages(body.images);

        return projectDAO.update(p);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") UUID id) {
        boolean ok = projectDAO.softDelete(id);
        if (!ok) throw new NotFoundException("Project not found: " + id);
        return Response.noContent().build();
    }
}
