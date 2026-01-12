package ec.edu.ups.ppw.gproyectos.services;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import ec.edu.ups.ppw.gproyectos.*;
import ec.edu.ups.ppw.gproyectos.bussines.GestionPersonas;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("persona")
public class PersonasService 
{
	
	private GestionPersonas gp;
	
	@GET
	@Produces("application/json")
	public List<Persona> getListaPersonas(){
		return gp.getPersonas();
	}

}
