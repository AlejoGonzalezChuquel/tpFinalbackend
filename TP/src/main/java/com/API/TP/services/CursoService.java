package com.API.TP.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.TP.entities.Alumno;
import com.API.TP.entities.Curso;
import com.API.TP.repositories.CursoRepository;

@Service
public class CursoService {
	
	//La anotiación Autowired le dice a Spring que debe inyectar una instancia del objeto de tipo CursoRepository en esta clase automáticamente.
	//En este caso específico,CursoRepository es una interfaz que extiend de JpaRepository.  Esto significa que es un repositorio que gestiona entidades del tipo Curso.
	//Este fragmento dice a Spring que debe proporcionar automáticamente una instancia de CursoRepository en esta clase, que será almacenada en la variable cursoRepository.
	//Una vez inyectada, la clase actual puede usar cursoRepository para ejecutar métodos que interactúen con la base de datos
	
	@Autowired
	private CursoRepository cursoRepository;
	

	public List<Curso> getCursos(){
		return cursoRepository.findAll();
	}
	
	// GET de un curso en particular (puede no existir).
	//Optional<Curso> indica que el método getCurso puede devolver un objeto de tipo Cursoo, pero no garantiza que siempre habrá un resultado.
	//Si no se encuentra ningún Curso con el id proporcionado, el método devolverá un Optional.empty(), es decir, un Optional que no contiene ningún valor.
	public Optional<Curso> getCurso(Long id){
		return cursoRepository.findById(id);
	}
	
	// POST de un curso nuevo.
	public void saveCurso(Curso curso) {
		cursoRepository.save(curso);
	}
	
	// POST de eliminación de curso.
	public void deleteCurso(Long id) {
		cursoRepository.deleteById(id);
	}

	
	// PUT de actualización de curso existente.
	public void updateCurso(Long id, Curso curso) {
		Curso updatedCurso = cursoRepository.findById(id). //Primero buscamos el curso en la base de datos usando el ID proporcionado como parametro.
				orElseThrow(() -> new RuntimeException("El Curso con ID " + curso.getId() + " no fue encontrado"));
		// Se lanza una excepción con un mensaje que indica que el curso no fue encontrado.
		
		// Actualizamos los parámetros del curso.
		updatedCurso.setAlumnos(curso.getAlumnos());
		updatedCurso.setDocente(curso.getDocente());
		updatedCurso.setFechaFin(curso.getFechaFin());
		updatedCurso.setFechaInicio(curso.getFechaInicio());
		updatedCurso.setPrecio(curso.getPrecio());
		updatedCurso.setTema(curso.getTema());
		
		
		// Una vez que se han actualizado los campos del curso, el método save de JpaRepository se llama para persistir los cambios en la base de datos.
		// Si el objeto updatedCurso ya existe en la base de datos (lo que es cierto en este caso), save lo actualiza con los nuevos valores. 
		cursoRepository.save(updatedCurso);
	}
	
	// GET de obtención de cursos que finaliza un día en específico.
	public List<Curso> findCursosByFechaFin(LocalDate fechaFin) {
		return cursoRepository.findCursosByFechaFin(fechaFin);
	}
	
	// GET de obtención de cursos vigentes de un docente.
	public List<Alumno> findAlumnosByCursosVigentes(Long docenteId) {
		LocalDate fechaActual = LocalDate.now();
		return cursoRepository.findAlumnosByCursosVigentes(docenteId, fechaActual);
	}
}
