package ec.edu.ups.services.dto;

public class ProgrammerProfileUpdateDTO {
    public String name;
    public String specialty;
    public String bio;
    public String avatarUrl;
    public String contactLinks;
    public Boolean deleted; // optional soft-delete flag
}
