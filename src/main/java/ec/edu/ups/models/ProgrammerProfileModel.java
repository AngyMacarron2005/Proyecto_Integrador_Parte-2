package ec.edu.ups.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "programmer_profiles")
public class ProgrammerProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserModel user;

    private String name;
    private String specialty;
    private String bio;
    private String avatarUrl;

    @Column(columnDefinition = "jsonb")
    private String contactLinks;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private boolean deleted = false;

    @OneToOne(mappedBy = "owner")
    private PortfolioModel portfolio;

    @OneToMany(mappedBy = "programmer")
    private List<ProgrammerAvailabilityModel> available;
}

