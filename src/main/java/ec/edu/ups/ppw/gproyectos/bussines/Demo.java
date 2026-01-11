package ec.edu.ups.ppw.gproyectos.bussines;

import ec.edu.ups.ppw.gproyectos.Persona;
import ec.edu.ups.ppw.gproyectos.dao.PersonaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Demo {
	@Inject
	private PersonaDAO daoPersona;
	
	@PostConstruct
	public void init()
	{
		System.out.println("Hola inicio");
		
		Persona p= new Persona();
		p.setCedula("0150535458");
		p.setNombre("Angy");
		p.setDireccion("Cuenca");
		
		daoPersona.insert(p);
	}
}
