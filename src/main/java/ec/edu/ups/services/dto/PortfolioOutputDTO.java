package ec.edu.ups.services.dto;


import java.util.List;

public class PortfolioOutputDTO {
    public String id;
    public String ownerId;
    public String title;
    public String description; // nullable
    public List<ProjectOutputDTO> project;
}