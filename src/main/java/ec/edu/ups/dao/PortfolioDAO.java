package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.models.PortfolioModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PortfolioDAO {

    @PersistenceContext
    private EntityManager em;

    public PortfolioModel create(PortfolioModel p) {
        em.persist(p);
        return p;
    }

    public PortfolioModel find(Long id) {
        return em.find(PortfolioModel.class, id);
    }

    public List<PortfolioModel> listActive() {
        return em.createQuery("SELECT p FROM PortfolioModel p WHERE p.deleted = false", PortfolioModel.class)
                 .getResultList();
    }

    public PortfolioModel update(PortfolioModel p) {
        return em.merge(p);
    }

    public boolean softDelete(Long id) {
        PortfolioModel p = find(id);
        if (p == null) return false;
        p.setDeleted(true);
        em.merge(p);
        return true;
    }
}
