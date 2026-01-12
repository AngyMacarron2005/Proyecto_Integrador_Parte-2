package ec.edu.ups.dao;

import ec.edu.ups.models.BookingModel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class BookingDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(BookingModel booking) {
        em.persist(booking);
    }
}
