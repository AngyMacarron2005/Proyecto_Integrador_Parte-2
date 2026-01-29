package ec.edu.ups.services.dto;

import java.util.List;

import ec.edu.ups.models.Enums.ParticipationType;
import ec.edu.ups.models.Enums.ProjectSection;

<<<<<<< HEAD:src/main/java/ec/edu/ups/services/dto/CategoriaDTO.java
public class CategoriaDTO {
    public Long portfolioId;

    public String title;
=======
public class ProjectCreateDTO {
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/services/dto/ProjectCreateDTO.java
    public String description;
    public String liveDemoUrl;     // nullable
    public String userId;
    public String repositoryUrl;   // nullable
    public String participation;   // originally: z.enum(ParticipationType)
    public String section;         // originally: z.enum(ProjectSection)
    public String title;

}