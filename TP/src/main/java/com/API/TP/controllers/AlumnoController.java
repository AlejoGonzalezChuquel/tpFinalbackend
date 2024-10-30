package com.API.TP.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.API.TP.services.AlumnoService;


//@RestController: Anotación que le indica a Spring que esta clase es un controlador REST, lo que significa que manejará solicitudes HTTP y devolverá datos en formato JSON
//@RequestMapping(path = "api/alumnos"): Define la ruta base para todas las solicitudes que maneje este controlador. Todas las rutas de esta clase estarán bajo el prefijo /api/alumnos. 

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping( path = "api/alumnos")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	private List<Alumno> getAlumnos(){
		return alumnoService.getAlumnos();
	}
	
	@GetMapping("/{alumnoId}")
	//@PathVariable("alumnoId"): Vincula el valor de la parte {alumnoId} de la ruta al parámetro Long alumnoId del método.
	private Optional<Alumno> getAlumno(@PathVariable("alumnoId") Long alumnoId) {
		return alumnoService.getAlumno(alumnoId);
	}
	
	@PostMapping("/add")
	//@RequestBody: Vincula el cuerpo de la solicitud HTTP (normalmente un JSON) al parámetro Alumno alumno, convirtiéndolo automáticamente en una instancia de la clase Alumno.
	private void addAlumno(@RequestBody Alumno alumno) {
		alumnoService.addAlumno(alumno);
	}
	
	@DeleteMapping("/delete/{alumnoId}")
	//@PathVariable("alumnoId"): Extrae el ID del alumno desde la ruta.
	private void deleteAlumno(@PathVariable("alumnoId") Long alumnoId) {
		alumnoService.deleteAlumno(alumnoId);
	}
	
	@PutMapping("/update/{alumnoId}")
	private void updateAlumno(@PathVariable("alumnoId") Long alumnoId, @RequestBody Alumno alumno) {
		alumnoService.updateAlumno(alumnoId, alumno);
	}
}
