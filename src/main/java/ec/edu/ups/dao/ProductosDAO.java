package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.models.UserModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public UserModel create(UserModel user) {
        em.persist(user);
        return user;
    }

    public UserModel find(Long id) {
        return em.find(UserModel.class, id);
    }

    public List<UserModel> list() {
        return em.createQuery("SELECT u FROM UserModel u", UserModel.class)
                 .getResultList();
    }

    public UserModel update(UserModel user) {
        return em.merge(user);
    }

    public boolean delete(Long id) {
        UserModel u = find(id);
        if (u == null) return false;
        em.remove(u);
        return true;
    }
}
