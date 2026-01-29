package ec.edu.ups.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.BookingModel;
import ec.edu.ups.models.Enums.Weekday;
import ec.edu.ups.models.ProgrammerProfileModel;
import ec.edu.ups.models.UserModel;
import ec.edu.ups.services.dto.AsesoriaCreateDTO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class AsesoriaDao {

    @PersistenceContext
    private EntityManager em;

    public BookingModel create(AsesoriaCreateDTO b, ProgrammerProfileModel pr, UserModel req) {
    	BookingModel entity = new BookingModel();
    	entity.setComment(b.comment);
    	entity.setDurationMin(b.durationMin);
    	entity.setDate(LocalDate.parse(b.date));
    	entity.setProgrammer(pr);
    	entity.setRequester(req);
    	entity.setWeekday(Weekday.FRI);
    	entity.setStatus("Pending");
    	
        em.persist(entity);
        return entity;
    }

    public BookingModel find(Long id) {
        return em.find(BookingModel.class, id);
    }

    public List<BookingModel> listAll() {
        return em.createQuery("SELECT b FROM BookingModel b", BookingModel.class)
                 .getResultList();
    }

    public List<BookingModel> listByRequester(Long requesterId) {
        return em.createQuery(
                "SELECT b FROM BookingModel b WHERE b.requester.id = :rid",
                BookingModel.class
        ).setParameter("rid", requesterId)
         .getResultList();
    }

    public List<BookingModel> listByProgrammer(UUID programmerId) {
        return em.createQuery(
                "SELECT b FROM BookingModel b WHERE b.programmer.id = :pid",
                BookingModel.class
        ).setParameter("pid", programmerId)
         .getResultList();
    }

    public BookingModel update(BookingModel b) {
        return em.merge(b);
    }


}
