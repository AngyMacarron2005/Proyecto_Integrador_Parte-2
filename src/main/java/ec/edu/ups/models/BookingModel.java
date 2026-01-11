package ec.edu.ups.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import ec.edu.ups.models.*;
import ec.edu.ups.models.Enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class BookingModel {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private UserModel requester;

    @ManyToOne
    @JoinColumn(name = "programmer_id")
    private ProgrammerProfileModel programmer;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private int hour;
    private int durationMin;
    private String comment;
    private String status = "PENDING";

    private LocalDateTime createdAt = LocalDateTime.now();
}
