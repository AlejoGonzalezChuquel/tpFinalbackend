package com.API.TP.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Alumno")
public class Alumno {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Utilizado para la generación de un valor incremental en el ID.
	private Long id;
	
	@Column(nullable = false) //La columna de nombre NO puede ser nula
	private String nombre;
	
	@Temporal(TemporalType.DATE) 
	@Column(nullable = false) //La columna de descripción NO puede ser nula.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //Indicamos el formato de fecha que va a esperar como fecha de nacimiento.
	private LocalDate fechaNacimiento;
	
	public Alumno() {} //Constructor vacío utilizado para JPA.
	
	public Alumno(String nombre, LocalDate fechaNacimiento) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
