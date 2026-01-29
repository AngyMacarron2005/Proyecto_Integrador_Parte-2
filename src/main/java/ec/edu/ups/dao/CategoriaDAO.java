package ec.edu.ups.dao;

import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.CategoriaModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CategoriaDAO {

    @PersistenceContext
    private EntityManager em;

    public CategoriaModel create(CategoriaModel p) {
        em.persist(p);
        return p;
    }

    public CategoriaModel find(UUID id) {
        return em.find(CategoriaModel.class, id);
    }

    public List<CategoriaModel> listActive() {
        return em.createQuery("SELECT p FROM ProjectModel p WHERE p.deleted = false", CategoriaModel.class)
                 .getResultList();
    }

    public List<CategoriaModel> listByPortfolio(Long portfolioId) {
        return em.createQuery(
                "SELECT p FROM ProjectModel p WHERE p.deleted = false AND p.portfolio.id = :pid",
                CategoriaModel.class
        ).setParameter("pid", portfolioId)
         .getResultList();
    }

    public CategoriaModel update(CategoriaModel p) {
        return em.merge(p);
    }

    public boolean softDelete(UUID id) {
        CategoriaModel p = find(id);
        if (p == null) return false;
        p.setDeleted(true);
        em.merge(p);
        return true;
    }
}
