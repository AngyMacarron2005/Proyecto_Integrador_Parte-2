package ec.edu.ups.services.dto;

import java.util.List;

import ec.edu.ups.models.Enums.ParticipationType;
import ec.edu.ups.models.Enums.ProjectSection;

public class ProjectCreateDTO {
    public String description;
    public String liveDemoUrl;     // nullable
    public String userId;
    public String repositoryUrl;   // nullable
    public String participation;   // originally: z.enum(ParticipationType)
    public String section;         // originally: z.enum(ProjectSection)
    public String title;

}