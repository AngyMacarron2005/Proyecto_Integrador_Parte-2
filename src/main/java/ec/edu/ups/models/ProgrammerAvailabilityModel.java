package ec.edu.ups.models;

import java.util.UUID;

import ec.edu.ups.models.Enums.Weekday;
import jakarta.persistence.*;

@Entity
@Table(
    name = "programmer_availability"
)
public class ProgrammerAvailabilityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "programmer_id", nullable = false)
    private ProgrammerProfileModel programmer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Weekday day;

    @Column(nullable = false)
    private int startMinutes;

    @Column(nullable = false)
    private int endMinutes;

    public ProgrammerAvailabilityModel() {}

    // getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public ProgrammerProfileModel getProgrammer() { return programmer; }
    public void setProgrammer(ProgrammerProfileModel programmer) { this.programmer = programmer; }

    public Weekday getDay() { return day; }
    public void setDay(Weekday day) { this.day = day; }

    public int getStartMinutes() { return startMinutes; }
    public void setStartMinutes(int startMinutes) { this.startMinutes = startMinutes; }

    public int getEndMinutes() { return endMinutes; }
    public void setEndMinutes(int endMinutes) { this.endMinutes = endMinutes; }
}
