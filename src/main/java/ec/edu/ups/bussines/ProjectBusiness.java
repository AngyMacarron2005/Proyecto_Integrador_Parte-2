package ec.edu.ups.bussines;

import ec.edu.ups.dao.ProjectDAO;
import ec.edu.ups.models.ProjectModel;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class ProjectBusiness {

    @Inject
    private ProjectDAO projectDAO;

    @PostConstruct
    public void init() {
        ProjectModel p = new ProjectModel();
        p.setTitle("Proyecto Demo");
        p.setDescription("Proyecto creado desde Business");

        projectDAO.insert(p);
        System.out.println("Proyecto creado 🌸");
    }
}
