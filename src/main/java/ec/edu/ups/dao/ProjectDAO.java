package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.models.ProjectModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProjectDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(ProjectModel project) {
		em.persist(project);
	}

	public List<ProjectModel> getProjects() {
		String jpql = "SELECT p FROM ProjectModel p";
		return em.createQuery(jpql, ProjectModel.class).getResultList();
	}
}
