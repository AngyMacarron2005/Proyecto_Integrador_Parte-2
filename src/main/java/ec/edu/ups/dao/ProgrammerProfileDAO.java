package ec.edu.ups.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.ProgrammerProfileModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProgrammerProfileDAO {

    @PersistenceContext
    private EntityManager em;

    public ProgrammerProfileModel create(ProgrammerProfileModel p) {
        em.persist(p);
        return p;
    }

    public ProgrammerProfileModel find(UUID id) {
        return em.find(ProgrammerProfileModel.class, id);
    }

    public List<ProgrammerProfileModel> listActive() {
        return em.createQuery(
                "SELECT p FROM ProgrammerProfileModel p WHERE p.deleted = false",
                ProgrammerProfileModel.class
        ).getResultList();
    }

    public ProgrammerProfileModel update(ProgrammerProfileModel p) {
        p.setUpdatedAt(LocalDateTime.now());
        return em.merge(p);
    }

    public boolean softDelete(UUID id) {
        ProgrammerProfileModel p = find(id);
        if (p == null) return false;
        p.setDeleted(true);
        p.setUpdatedAt(LocalDateTime.now());
        em.merge(p);
        return true;
    }
}
