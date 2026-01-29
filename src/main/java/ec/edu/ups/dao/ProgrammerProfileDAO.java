package ec.edu.ups.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.PortfolioModel;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.ProgrammerProfileCreateDTO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProgrammerProfileDAO {

	@PersistenceContext
	private EntityManager em;

	public ProgrammerProfileModel create(ProgrammerProfileCreateDTO p, UserModel user) {
		ProgrammerProfileModel entity = new ProgrammerProfileModel();

		PortfolioModel portfolio = new PortfolioModel();
		portfolio.setDescription("Nuevo portafolio de " + user.getDisplayName());
		portfolio.setTitle("Portafolio de " + user.getDisplayName());
		
		entity.setAvatarUrl("");
		entity.setBio("");
		entity.setContactLinks("");
		entity.setName(user.getDisplayName());
		entity.setSpecialty("Frontend");
		entity.setUser(user);
		entity.setPortfolio(portfolio);

		em.persist(entity);
		return entity;
	}

	public ProgrammerProfileModel find(Long id) {
		return em.find(ProgrammerProfileModel.class, id);
	}

	public List<ProgrammerProfileModel> list() {
		return em.createQuery("SELECT p FROM ProgrammerProfileModel p ", ProgrammerProfileModel.class).getResultList();
	}

	public ProgrammerProfileModel update(ProgrammerProfileModel p) {
		p.setUpdatedAt(LocalDateTime.now());
		return em.merge(p);
	}
}
