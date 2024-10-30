package com.API.TP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.TP.entities.Docente;
import com.API.TP.repositories.DocenteRepository;

@Service
public class DocenteService {
	
	//La anotiación Autowired le dice a Spring que debe inyectar una instancia del objeto de tipo DocenteRepository en esta clase automáticamente.
	//En este caso específico,DocenteRepository es una interfaz que extiend de JpaRepository.  Esto significa que es un repositorio que gestiona entidades del tipo Docente.
	//Este fragmento dice a Spring que debe proporcionar automáticamente una instancia de DocenteRepository en esta clase, que será almacenada en la variable docenteRepository.
	//Una vez inyectada, la clase actual puede usar docenteRepository para ejecutar métodos que interactúen con la base de datos
	
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	// GET de todos los docentes.
	public List<Docente> getDocentes() {
		return docenteRepository.findAll();
	}
	
	// GET de un docente en particular (puede no existir).
	//Optional<Tema> indica que el método getDocente puede devolver un objeto de tipo Docente, pero no garantiza que siempre habrá un resultado.
	//Si no se encuentra ningún Docente con el id proporcionado, el método devolverá un Optional.empty(), es decir, un Optional que no contiene ningún valor.
	public Optional<Docente> getDocente(Long id) {
		return docenteRepository.findById(id);
	}
	
	// POST de un nuevo docente.
	public void addDocente(Docente docente) {
		docenteRepository.save(docente);
	}
	
	// DELETE de un docente.
	public void deleteDocente(Long id) {
		docenteRepository.deleteById(id);
	}
	
	// PUT de actualización de un docente existente.
	public void updateDocente(Long id, Docente docente) {
		Docente updatedDocente = docenteRepository.findById(id) //Primero buscamos el docente en la base de datos usando el ID proporcionado como parametro.
				.orElseThrow(() -> new RuntimeException("El Docente con ID " + docente.getId() + " no fue encontrado"));
		// Se lanza una excepción con un mensaje que indica que el docente no fue encontrado.
		
		// Actualizamos los parámetros del tema.
		updatedDocente.setNombre(docente.getNombre());
		updatedDocente.setLegajo(docente.getLegajo());
		
		// Una vez que se han actualizado los campos del docente, el método save de JpaRepository se llama para persistir los cambios en la base de datos.
		// Si el objeto updatedDocente ya existe en la base de datos (lo que es cierto en este caso), save lo actualiza con los nuevos valores. 
		docenteRepository.save(updatedDocente);
	}
}
