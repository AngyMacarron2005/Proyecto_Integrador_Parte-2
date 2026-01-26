package ec.edu.ups.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ec.edu.ups.models.Enums.ParticipationType;
import ec.edu.ups.models.Enums.ProjectSection;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioModel portfolio;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectSection section;

    @Enumerated(EnumType.STRING)
    private ParticipationType participation;

    @ElementCollection
    @CollectionTable(
        name = "project_technologies",
        joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "technology")
    private List<String> technologies;

    private String repositoryUrl;
    private String liveDemoUrl;

    @ElementCollection
    @CollectionTable(
        name = "project_images",
        joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "image_url")
    private List<String> images;

    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean deleted = false;

    /* =====================
       GETTERS & SETTERS
       ===================== */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PortfolioModel getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioModel portfolio) {
        this.portfolio = portfolio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectSection getSection() {
        return section;
    }

    public void setSection(ProjectSection section) {
        this.section = section;
    }

    public ParticipationType getParticipation() {
        return participation;
    }

    public void setParticipation(ParticipationType participation) {
        this.participation = participation;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLiveDemoUrl() {
        return liveDemoUrl;
    }

    public void setLiveDemoUrl(String liveDemoUrl) {
        this.liveDemoUrl = liveDemoUrl;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
