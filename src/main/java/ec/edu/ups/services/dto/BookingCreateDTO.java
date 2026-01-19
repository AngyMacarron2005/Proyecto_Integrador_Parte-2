package ec.edu.ups.services.dto;

import java.util.UUID;

import ec.edu.ups.models.Enums.Weekday;

public class BookingCreateDTO {
    public Long requesterId;
    public UUID programmerId;

    public String date;     // "2026-01-18"
    public Weekday weekday;

    public int hour;        // 0..23
    public int durationMin; // e.g. 60
    public String comment;

    public String status;   // optional
}
