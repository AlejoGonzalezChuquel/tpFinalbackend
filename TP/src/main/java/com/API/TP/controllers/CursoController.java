package com.API.TP.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.TP.entities.Alumno;
import com.API.TP.entities.Curso;
import com.API.TP.services.CursoService;

//@RestController: Anotación que le indica a Spring que esta clase es un controlador REST, lo que significa que manejará solicitudes HTTP y devolverá datos en formato JSON
//@RequestMapping(path = "api/cursos"): Define la ruta base para todas las solicitudes que maneje este controlador. Todas las rutas de esta clase estarán bajo el prefijo /api/cursos.

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping( path = "api/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public List<Curso> getCursos(){
		return cursoService.getCursos();
	}
	
	@GetMapping("/{cursoId}")
	//@PathVariable("cursoId"): Vincula el valor de la parte {cursoId} de la ruta al parámetro Long cursoId del método
	public Optional<Curso> getCurso(@PathVariable("cursoId") Long cursoId) {
		return cursoService.getCurso(cursoId);
	}
	
	@PostMapping("/add")
	//@RequestBody: Vincula el cuerpo de la solicitud HTTP (normalmente un JSON) al parámetro Curso curso, convirtiéndolo automáticamente en una instancia de la clase Curso.
	public void saveCurso(@RequestBody Curso curso) {
		cursoService.saveCurso(curso);
	}
	
	@DeleteMapping("/delete/{cursoId}")
	//@PathVariable("cursoId"): Extrae el ID del curso desde la ruta.
	public void deleteCurso(@PathVariable("cursoId") Long cursoId) {
		cursoService.deleteCurso(cursoId);
	}
	
	@PutMapping("/update/{cursoId}")
	public void updateCurso(@PathVariable("cursoId") Long cursoId, @RequestBody Curso curso) {
		cursoService.updateCurso(cursoId, curso);
	}
	
	@GetMapping("/fecha-fin/{fechaFin}")
	//@PathVariable("fechaFin"):Indica que el valor de la parte {fechaFin} de la URL se asignará al parámetro fechaFin del método. Este valor será pasado como un String en la URL, 
	//pero lo queremos como un LocalDate.
	//@LocalDateTimeFormat(pattern = "yyyy-MM-dd"): anotación se usa para formatear correctamente ese texto a un objeto LocalDate, especificando el formato yyyy-MM-dd.
	public List<Curso> getCursosByFechaFin(@PathVariable("fechaFin") @DateTimeFormat( pattern = "yyyy-MM-dd") LocalDate fechaFin) {
		return cursoService.findCursosByFechaFin(fechaFin);
	}
	
	@GetMapping("/vigentes/docente/{docenteId}/alumnos")
	//@PathVariable("docenteId": Indica que el valor de la parte {docenteId} de la URL se asignará al parámetro docenteId del método.
	public List<Alumno> getAlumnosByCursosVigentes(@PathVariable("docenteId") Long docenteId) {
		return cursoService.findAlumnosByCursosVigentes(docenteId);
	}
}
