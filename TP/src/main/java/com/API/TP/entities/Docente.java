package com.API.TP.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Docente")
public class Docente {
	
	@Id //Establecer que se trata de la clave primaria.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Utilizado para la generación de un valor incremental en el ID.
	private Long id;
	
	@Column(nullable = false) //La columna de legajo NO puede ser nula.
	private Long legajo;
	
	@Column(nullable=false)
	private String nombre;
	
	public Docente() {} //Constructor vacío utilizado para JPA.
	
	public Docente(Long legajo, String nombre) {
		this.legajo = legajo;
		this.nombre = nombre;
	}
	
	//Establecimiento de Getters y Setters.
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLegajo() {
		return legajo;
	}

	public void setLegajo(Long legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
