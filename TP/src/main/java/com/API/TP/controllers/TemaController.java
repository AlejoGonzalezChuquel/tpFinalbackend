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

import com.API.TP.entities.Tema;
import com.API.TP.services.TemaService;

//@RestController: Anotación que le indica a Spring que esta clase es un controlador REST, lo que significa que manejará solicitudes HTTP y devolverá datos en formato JSON
//@RequestMapping(path = "api/temas"): Define la ruta base para todas las solicitudes que maneje este controlador. Todas las rutas de esta clase estarán bajo el prefijo /api/temas.

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping( path = "api/temas")
public class TemaController {
	
	@Autowired
	private TemaService temaService;
	
	@GetMapping
	private List<Tema> getTemas(){
		return temaService.getTemas();
	}
	
	@GetMapping("/{temaId}")
	//@PathVariable("temaId"): Vincula el valor de la parte {temaId} de la ruta al parámetro Long temaId del método.
	private Optional<Tema> getTema(@PathVariable("temaId") Long temaId) {
		return temaService.getTema(temaId);
	}
	
	@PostMapping("/add")
	//@RequestBody: Vincula el cuerpo de la solicitud HTTP (normalmente un JSON) al parámetro Tema tema, convirtiéndolo automáticamente en una instancia de la clase Tema.
	private void addTema(@RequestBody Tema tema) {
		temaService.addTema(tema);
	}
	
	@DeleteMapping("/delete/{temaId}")
	//@PathVariable("temaId"): Extrae el ID del tema desde la ruta.
	private void deleteTema(@PathVariable("temaId") Long temaId) {
		temaService.deleteTema(temaId);
	}
	
	@PutMapping("/update/{temaId}")
	private void updateTema(@PathVariable("temaId") Long temaId, @RequestBody Tema tema) {
		temaService.updateTema(temaId, tema);
	}

}
