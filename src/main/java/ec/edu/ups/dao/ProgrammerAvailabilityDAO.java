package ec.edu.ups.dao;

import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.ProgrammerAvailabilityModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProgrammerAvailabilityDAO {

    @PersistenceContext
    private EntityManager em;

    public ProgrammerAvailabilityModel create(ProgrammerAvailabilityModel a) {
        em.persist(a);
        return a;
    }

    public ProgrammerAvailabilityModel find(UUID id) {
        return em.find(ProgrammerAvailabilityModel.class, id);
    }

    public List<ProgrammerAvailabilityModel> listAll() {
        return em.createQuery("SELECT a FROM ProgrammerAvailabilityModel a", ProgrammerAvailabilityModel.class)
                 .getResultList();
    }

    public List<ProgrammerAvailabilityModel> listByProgrammer(UUID programmerId) {
        return em.createQuery(
                "SELECT a FROM ProgrammerAvailabilityModel a WHERE a.programmer.id = :pid",
                ProgrammerAvailabilityModel.class
        ).setParameter("pid", programmerId)
         .getResultList();
    }

    public ProgrammerAvailabilityModel update(ProgrammerAvailabilityModel a) {
        return em.merge(a);
    }

    public boolean delete(UUID id) {
        ProgrammerAvailabilityModel a = find(id);
        if (a == null) return false;
        em.remove(a);
        return true;
    }
}
