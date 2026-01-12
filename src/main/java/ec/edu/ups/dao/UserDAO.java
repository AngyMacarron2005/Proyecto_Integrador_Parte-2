package ec.edu.ups.dao;

import ec.edu.ups.models.UserModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(UserModel user) {
        em.persist(user);
    }

    public UserModel findById(Long id) {
        return em.find(UserModel.class, id);
    }
}
