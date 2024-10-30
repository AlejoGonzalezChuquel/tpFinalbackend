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

import com.API.TP.entities.Docente;
import com.API.TP.services.DocenteService;


//@RestController: Anotación que le indica a Spring que esta clase es un controlador REST, lo que significa que manejará solicitudes HTTP y devolverá datos en formato JSON
//@RequestMapping(path = "api/docentes"): Define la ruta base para todas las solicitudes que maneje este controlador. Todas las rutas de esta clase estarán bajo el prefijo /api/docentes.

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping( path = "api/docentes")
public class DocenteController {
	
	
	@Autowired
	private DocenteService docenteService;
	
	@GetMapping
	private List<Docente> getDocentes() {
		return docenteService.getDocentes();
	}
	
	@GetMapping("/{docenteId}")
	//@PathVariable("docenteId"): Vincula el valor de la parte {docenteId} de la ruta al parámetro Long docenteId del método.
	private Optional<Docente> getDocente(@PathVariable("docenteId") Long docenteId) {
		return docenteService.getDocente(docenteId);
	}
	
	@PostMapping("/add")
	//@RequestBody: Vincula el cuerpo de la solicitud HTTP (normalmente un JSON) al parámetro Docente docente, convirtiéndolo automáticamente en una instancia de la clase Docente.
	private void addDocente(@RequestBody Docente docente) {
		docenteService.addDocente(docente);
	}
	
	@DeleteMapping("/delete/{docenteId}")
	//@PathVariable("docenteId"): Extrae el ID del docente desde la ruta.
	private void deleteDocente(@PathVariable("docenteId") Long docenteId) {
		docenteService.deleteDocente(docenteId);
	}
	
	@PutMapping("/update/{docenteId}")
	private void updateDocente(@PathVariable("docenteId") Long docenteId, @RequestBody Docente docente) {
		docenteService.updateDocente(docenteId, docente);
	}

}
