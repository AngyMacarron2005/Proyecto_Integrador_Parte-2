package ec.edu.ups.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import ec.edu.ups.models.Enums.Weekday;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private UserModel requester;

    @ManyToOne
    @JoinColumn(name = "programmer_id", nullable = false)
    private ProgrammerProfileModel programmer;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private int hour;
    private int durationMin;

    @Column(length = 500)
    private String comment;

    private String status = "PENDING";

    private LocalDateTime createdAt = LocalDateTime.now();

    /* =====================
       GETTERS & SETTERS
       ===================== */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserModel getRequester() {
        return requester;
    }

    public void setRequester(UserModel requester) {
        this.requester = requester;
    }

    public ProgrammerProfileModel getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerProfileModel programmer) {
        this.programmer = programmer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
