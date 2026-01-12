package ec.edu.ups.bussines;

import ec.edu.ups.dao.BookingDAO;
import ec.edu.ups.models.BookingModel;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class BookingBusiness {

    @Inject
    private BookingDAO bookingDAO;

    @PostConstruct
    public void init() {
        BookingModel b = new BookingModel();
        b.setStatus("PENDING");
        b.setDurationMin(60);

        bookingDAO.insert(b);
        System.out.println("Booking creado");
    }
}
