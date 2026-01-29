//package ec.edu.ups.dao;
//
//import java.util.List;
//import java.util.UUID;
//
//import ec.edu.ups.models.BookingModel;
//import jakarta.ejb.Stateless;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//@Stateless
//public class BookingDAO {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public BookingModel create(BookingModel b) {
//        em.persist(b);
//        return b;
//    }
//
//    public BookingModel find(UUID id) {
//        return em.find(BookingModel.class, id);
//    }
//
//    public List<BookingModel> listAll() {
//        return em.createQuery("SELECT b FROM BookingModel b", BookingModel.class)
//                 .getResultList();
//    }
//
//    public List<BookingModel> listByRequester(Long requesterId) {
//        return em.createQuery(
//                "SELECT b FROM BookingModel b WHERE b.requester.id = :rid",
//                BookingModel.class
//        ).setParameter("rid", requesterId)
//         .getResultList();
//    }
//
//    public List<BookingModel> listByProgrammer(UUID programmerId) {
//        return em.createQuery(
//                "SELECT b FROM BookingModel b WHERE b.programmer.id = :pid",
//                BookingModel.class
//        ).setParameter("pid", programmerId)
//         .getResultList();
//    }
//
//    public BookingModel update(BookingModel b) {
//        return em.merge(b);
//    }
//
//    public boolean delete(UUID id) {
//        BookingModel b = find(id);
//        if (b == null) return false;
//        em.remove(b);
//        return true;
//    }
//}
