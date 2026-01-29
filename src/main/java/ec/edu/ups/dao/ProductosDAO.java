package ec.edu.ups.dao;

import java.util.List;

<<<<<<< HEAD:src/main/java/ec/edu/ups/dao/ProductosDAO.java
import ec.edu.ups.models.ProductosModel;
=======
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.UserCreateDTO;
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/dao/UserDAO.java
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductosDAO {

    @PersistenceContext
    private EntityManager em;

<<<<<<< HEAD:src/main/java/ec/edu/ups/dao/ProductosDAO.java
    public ProductosModel create(ProductosModel user) {
        em.persist(user);
        return user;
=======
    public UserModel create(UserCreateDTO dto) {
    	 UserModel entity = new UserModel();
    	    entity.setFirebaseUid(dto.firebaseUid);
    	    entity.setEmail(dto.email);
    	    entity.setDisplayName(dto.displayName);
    	    entity.setPicture(dto.foto_perfil); // DTO name -> entity column "picture"


    	    entity.setRole("USER");
        em.persist(entity);
        return entity;
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/dao/UserDAO.java
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

<<<<<<< HEAD:src/main/java/ec/edu/ups/dao/ProductosDAO.java
    public boolean delete(Long id) {
        ProductosModel u = find(id);
        if (u == null) return false;
        em.remove(u);
        return true;
    }
=======
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/dao/UserDAO.java
}
