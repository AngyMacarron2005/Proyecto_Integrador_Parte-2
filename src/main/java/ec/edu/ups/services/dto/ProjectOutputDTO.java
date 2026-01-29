package ec.edu.ups.services.dto;

public class ProjectOutputDTO {
	public String id;
    public String title;
    public String description;

    public String section;         // originally: z.enum(ProjectSection)
    public String participation;   // originally: z.enum(ParticipationType)

    public String repositoryUrl;   // nullable
    public String liveDemoUrl;     // nullable

    public String portafolioId;
}
