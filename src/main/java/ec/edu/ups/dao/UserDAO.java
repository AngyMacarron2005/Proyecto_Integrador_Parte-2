package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.UserCreateDTO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public UserModel create(UserCreateDTO dto) {
    	 UserModel entity = new UserModel();
    	    entity.setFirebaseUid(dto.firebaseUid);
    	    entity.setEmail(dto.email);
    	    entity.setDisplayName(dto.displayName);
    	    entity.setPicture(dto.foto_perfil); // DTO name -> entity column "picture"


    	    entity.setRole("USER");
        em.persist(entity);
        return entity;
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

}
