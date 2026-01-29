package ec.edu.ups.services.dto;

<<<<<<< HEAD:src/main/java/ec/edu/ups/services/dto/ProductosDTO.java
public class ProductosDTO {
=======
import java.util.List;

public class ProgrammerProfileCreateDTO {
>>>>>>> c96b37f (29-01-2026 07:14):src/main/java/ec/edu/ups/services/dto/ProgrammerProfileCreateDTO.java
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
