package ec.edu.ups.dao;

import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.ProjectModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProjectDAO {

    @PersistenceContext
    private EntityManager em;

    public ProjectModel create(ProjectModel p) {
        em.persist(p);
        return p;
    }

    public ProjectModel find(UUID id) {
        return em.find(ProjectModel.class, id);
    }

    public List<ProjectModel> listActive() {
        return em.createQuery("SELECT p FROM ProjectModel p WHERE p.deleted = false", ProjectModel.class)
                 .getResultList();
    }

    public List<ProjectModel> listByPortfolio(Long portfolioId) {
        return em.createQuery(
                "SELECT p FROM ProjectModel p WHERE p.deleted = false AND p.portfolio.id = :pid",
                ProjectModel.class
        ).setParameter("pid", portfolioId)
         .getResultList();
    }

    public ProjectModel update(ProjectModel p) {
        return em.merge(p);
    }

    public boolean softDelete(UUID id) {
        ProjectModel p = find(id);
        if (p == null) return false;
        p.setDeleted(true);
        em.merge(p);
        return true;
    }
}
