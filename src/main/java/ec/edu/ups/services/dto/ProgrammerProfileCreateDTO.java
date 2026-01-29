package ec.edu.ups.services.dto;

import java.util.List;

public class ProgrammerProfileCreateDTO {
    public Long userId;
    public String bio;
    public String specialty;
    public List<DisponibilidadDTO> disponibilidad;

    public static class DisponibilidadDTO {
        public String day;           // originally: z.enum(Weekday)
        public String startMinutes;  // originally string
        public String endMinutes;    // originally string

        public DisponibilidadDTO() {}
    }
}
