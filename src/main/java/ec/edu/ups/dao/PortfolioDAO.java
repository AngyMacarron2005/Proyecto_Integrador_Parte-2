package ec.edu.ups.dao;

import ec.edu.ups.models.PortfolioModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PortfolioDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(PortfolioModel portfolio) {
        em.persist(portfolio);
    }

    public PortfolioModel findById(Long id) {
        return em.find(PortfolioModel.class, id);
    }
}
