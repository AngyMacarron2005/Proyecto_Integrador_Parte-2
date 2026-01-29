package ec.edu.ups.services.dto;

import ec.edu.ups.models.ProgrammerAvailabilityModel;

public class AvailabilityOutputDTO {
	 public String day;
	 public Integer startMinuts;
	 public Integer endMinuts;
	 
	  static AvailabilityOutputDTO fromModel(ProgrammerAvailabilityModel mod) {
		 AvailabilityOutputDTO dto = new AvailabilityOutputDTO();
		 dto.day = mod.getDay().toString();
		 dto.startMinuts = mod.getStartMinutes();
		 dto.endMinuts = mod.getEndMinutes();
		 return dto;
	 }
}
