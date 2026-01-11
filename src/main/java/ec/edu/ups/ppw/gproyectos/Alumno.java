package ec.edu.ups.ppw.gproyectos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Alumno implements Serializable
{
	private static final long serialVersion=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_alumno")
	private int idAlumno;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_domicilio")
	private Domicilio domicilio;
	
}

