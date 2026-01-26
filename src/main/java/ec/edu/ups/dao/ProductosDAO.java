package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.models.ProductosModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductosDAO {

    @PersistenceContext
    private EntityManager em;

    public ProductosModel create(ProductosModel user) {
        em.persist(user);
        return user;
    }

    public ProductosModel find(Long id) {
        return em.find(ProductosModel.class, id);
    }

    public List<ProductosModel> list() {
        return em.createQuery("SELECT u FROM UserModel u", ProductosModel.class)
                 .getResultList();
    }

    public ProductosModel update(ProductosModel user) {
        return em.merge(user);
    }

    public boolean delete(Long id) {
        ProductosModel u = find(id);
        if (u == null) return false;
        em.remove(u);
        return true;
    }
}
