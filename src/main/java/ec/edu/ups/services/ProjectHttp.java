package ec.edu.ups.services;

import java.util.List;

import ec.edu.ups.dao.ProjectDAO;
import ec.edu.ups.models.ProjectModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("project")
public class ProjectHttp {

	@Inject
    private ProjectDAO projectBusiness;
	
	@GET
	@Produces("application/json")
	public List<ProjectModel> getProjects(){
		return projectBusiness.getProjects();
	}
}
