package com.API.TP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.TP.entities.Tema;
import com.API.TP.repositories.TemaRepository;

@Service 
public class TemaService {
	
	//La anotiación Autowired le dice a Spring que debe inyectar una instancia del objeto de tipo TemaRepository en esta clase automáticamente.
	//En este caso específico,TemaRepository es una interfaz que extiend de JpaRepository.  Esto significa que es un repositorio que gestiona entidades del tipo Tema.
	//Este fragmento dice a Spring que debe proporcionar automáticamente una instancia de TemaRepository en esta clase, que será almacenada en la variable temaRepository.
	//Una vez inyectada, la clase actual puede usar temaRepository para ejecutar métodos que interactúen con la base de datos
	

	@Autowired
	private TemaRepository temaRepository;
	
	//GET de todos los temas.
	public List<Tema> getTemas() {
		return temaRepository.findAll();
	}
	
	// GET de un tema en particular (puede no existir).
	//Optional<Tema> indica que el método getTema puede devolver un objeto de tipo Tema, pero no garantiza que siempre habrá un resultado.
	//Si no se encuentra ningún Tema con el id proporcionado, el método devolverá un Optional.empty(), es decir, un Optional que no contiene ningún valor.
	public Optional<Tema> getTema(Long id) {
		return temaRepository.findById(id);
	}
	
	//POST de un tema nuevo.
	public void addTema(Tema tema) {
		temaRepository.save(tema);
	}
	
	//POST de eliminación de curso.
	public void deleteTema(Long id) {
		temaRepository.deleteById(id);
	}
	
	// PUT de actualización de tema existente.
	public void updateTema(Long id, Tema tema) {
		Tema updatedTema = temaRepository.findById(id). //Primero buscamos el tema en la base de datos usando el ID proporcionado como parametro.
				orElseThrow(() -> new RuntimeException("El Tema con ID " + tema.getId() + " no fue encontrado"));
		// Se lanza una excepción con un mensaje que indica que el tema no fue encontrado.
		
		// Actualizamos los parámetros del tema.
		updatedTema.setNombre(tema.getNombre());
		updatedTema.setDescripcion(tema.getDescripcion());
		
		// Una vez que se han actualizado los campos del tema, el método save de JpaRepository se llama para persistir los cambios en la base de datos.
		// Si el objeto updatedTema ya existe en la base de datos (lo que es cierto en este caso), save lo actualiza con los nuevos valores. 
		temaRepository.save(updatedTema);
	}

}
