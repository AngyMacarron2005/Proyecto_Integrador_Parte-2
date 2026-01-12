package ec.edu.ups.ppw.gproyectos.bussines;

import java.util.List;
import ec.edu.ups.ppw.gproyectos.*;
import ec.edu.ups.ppw.gproyectos.dao.PersonaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPersonas {
	
	@Inject
	private PersonaDAO daoPersona;
	public List<Persona> getPersonas()
	{
		return daoPersona.getAll();
	}
}
