package ec.edu.ups.services.dto;

import java.util.List;

import ec.edu.ups.models.Enums.ParticipationType;
import ec.edu.ups.models.Enums.ProjectSection;

public class ProjectCreateDTO {
    public Long portfolioId;

    public String title;
    public String description;
    public ProjectSection section;
    public ParticipationType participation;

    public List<String> technologies;
    public String repositoryUrl;
    public String liveDemoUrl;

    public List<String> images;
}
