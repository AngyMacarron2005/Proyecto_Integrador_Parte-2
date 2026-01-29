package ec.edu.ups.services.dto;

import java.util.List;

import ec.edu.ups.models.ProgrammerProfileModel;

public class ProgrammerOutputDTO {
    public String id;
    public String userId;
    public String name;

    public String specialty;     // nullable
    public String bio;           // nullable
    public String avatarUrl;     // nullable
    public String contactLinks;  // nullable

    public String portafolioId;
    public List<AvailabilityOutputDTO> disponibilidad;

    public ProgrammerOutputDTO() {}
    
    public static ProgrammerOutputDTO fromModel(ProgrammerProfileModel mod) {
    	return new ProgrammerOutputDTO() {{
			id = mod.getId().toString();
			userId = mod.getUser().getId().toString();
			name = mod.getName();
			specialty = mod.getSpecialty();
			bio = mod.getBio();
			avatarUrl = mod.getAvatarUrl();
			contactLinks = mod.getContactLinks();
			portafolioId = mod.getPortfolio().getId().toString();
			disponibilidad = mod.getAvailable().stream()
					.map(AvailabilityOutputDTO::fromModel)
					.toList();
		}};
    }
    
}