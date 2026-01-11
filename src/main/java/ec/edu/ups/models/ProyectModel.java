package ec.edu.ups.models;

import java.time.LocalDateTime;
import java.util.*;
import ec.edu.ups.models.*;
import ec.edu.ups.models.Enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class ProyectModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private PortfolioModel portfolio;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectSection section;

    @Enumerated(EnumType.STRING)
    private ParticipationType participation;

    @ElementCollection
    private List<String> technologies;

    private String repositoryUrl;
    private String liveDemoUrl;

    @ElementCollection
    private List<String> images;

    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean deleted = false;
}
