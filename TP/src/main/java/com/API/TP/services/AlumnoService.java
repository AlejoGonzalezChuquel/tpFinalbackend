package com.API.TP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.TP.entities.Alumno;
import com.API.TP.repositories.AlumnoRepository;

@Service
public class AlumnoService {
	
	//La anotiación Autowired le dice a Spring que debe inyectar una instancia del objeto de tipo AlumnoRepository en esta clase automáticamente.
	//En este caso específico, AlumnoRepository es una interfaz que extiend de JpaRepository.  Esto significa que es un repositorio que gestiona entidades del tipo Alumno.
	//Este fragmento dice a Spring que debe proporcionar automáticamente una instancia de AlumnoRepository en esta clase, que será almacenada en la variable alumnoRepository.
	//Una vez inyectada, la clase actual puede usar alumnoRepository para ejecutar métodos que interactúen con la base de datos
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	// GET de todos los alumnos existentes.
	public List<Alumno> getAlumnos(){
		return alumnoRepository.findAll();
	}
	
	// GET de un alumno en específico (puede no existir).
	//Optional<Alumno> indica que el método getAlumno puede devolver un objeto de tipo Alumno, pero no garantiza que siempre habrá un resultado.
	//Si no se encuentra ningún Alumno con el id proporcionado, el método devolverá un Optional.empty(), es decir, un Optional que no contiene ningún valor.
	
	public Optional<Alumno> getAlumno(Long id) {
		return alumnoRepository.findById(id);
	}
	
	// POST de un alumno nuevo.
	public void addAlumno(Alumno alumno) {
		alumnoRepository.save(alumno);
	}
	
	// DELETE de eliminación de alumno.
	public void deleteAlumno(Long id) {
			alumnoRepository.deleteById(id);
	}
	
	//PUT de un alumno actualizado.
	public void updateAlumno(Long id, Alumno alumno) {
		
		Alumno updatedAlumno = alumnoRepository.findById(id) //Primero buscamos el alumno en la base de datos usando el ID proporcionado como parametro.
				.orElseThrow(() -> new RuntimeException("El Alumno con ID " + alumno.getId() + " no fue encontrado"));
		// Se lanza una excepción con un mensaje que indica que el alumno no fue encontrado.
			
		// Actualizamos los parámetros de nombre y fecha de nacimiento pasados como parámetros.
		updatedAlumno.setNombre(alumno.getNombre());
		updatedAlumno.setFechaNacimiento(alumno.getFechaNacimiento());
			
		// Una vez que se han actualizado los campos del alumno, el método save de JpaRepository se llama para persistir los cambios en la base de datos.
		// Si el objeto updatedAlumno ya existe en la base de datos (lo que es cierto en este caso), save lo actualiza con los nuevos valores. 
		alumnoRepository.save(updatedAlumno);
	
	}

}
