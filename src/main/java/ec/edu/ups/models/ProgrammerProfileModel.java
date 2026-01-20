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
    private String contactLinks;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private boolean deleted = false;

    @OneToOne(mappedBy = "owner")
    private PortfolioModel portfolio;

    @OneToMany(mappedBy = "programmer")
    private List<ProgrammerAvailabilityModel> available;
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UserModel getUser() { return user; }
    public void setUser(UserModel user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getContactLinks() { return contactLinks; }
    public void setContactLinks(String contactLinks) { this.contactLinks = contactLinks; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public PortfolioModel getPortfolio() { return portfolio; }
    public void setPortfolio(PortfolioModel portfolio) { this.portfolio = portfolio; }

    public List<ProgrammerAvailabilityModel> getAvailable() { return available; }
    public void setAvailable(List<ProgrammerAvailabilityModel> available) { this.available = available; }
}

