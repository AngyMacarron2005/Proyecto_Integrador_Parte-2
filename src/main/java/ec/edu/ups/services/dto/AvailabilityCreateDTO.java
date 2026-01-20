package ec.edu.ups.services.dto;

import java.util.UUID;

import ec.edu.ups.models.Enums.Weekday;

public class AvailabilityCreateDTO {
    public UUID programmerId;
    public Weekday day;
    public int startMinutes;
    public int endMinutes;
}
