package ec.edu.ups.services.dto;

import ec.edu.ups.models.BookingModel;

public class AsesoriaOutputDTO {
	public String id;
	public String comment;         
	public String date;
	public Integer durationMin;
	public Integer hour;
    public String programmerId;
    public String responseMessage;  
    public String status;
    
    public AsesoriaOutputDTO() {}
    public static AsesoriaOutputDTO fromModel(BookingModel md) {
    	AsesoriaOutputDTO dto = new AsesoriaOutputDTO();
dto.id = md.getId().toString();
dto.comment = md.getComment();
dto.date = md.getDate().toString();
dto.durationMin = md.getDurationMin();
dto.hour = md.getHour();
dto.programmerId = md.getProgrammer().getId().toString();
dto.responseMessage = md.getResponseMessage();
dto.status = md.getStatus();
return dto;
}
}
