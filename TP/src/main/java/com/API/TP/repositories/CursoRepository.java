package com.API.TP.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.API.TP.entities.Alumno;
import com.API.TP.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	//El @Query permite escribir una consulta JPQL personalizada. Aquí se seleccionaran los cursos donde la fecha de finalización coincide con la pasada por parametro.
	//Se hace un SELECT a la tabla donde:
		//El DATE(c.fechaFin) nos permite quedarnos solo con la parte de la fecha.
		//Lo comparamos con :fechaFin que es un parametro en la consulta que se sustituye por el valor pasado a la funcción.
	//findCursosByFechaDin: este método devuelve una lista de cursos (List<Curso>) que tienen una fechaFin igual a la padada como parámetro.
	
	@Query("SELECT c FROM Curso c WHERE DATE(c.fechaFin) = :fechaFin")
    List<Curso> findCursosByFechaFin(@Param("fechaFin") LocalDate fechaFin);
	
	//Esta consulta permite seleccionar a los alumnos que estan en un curso. El JOIN permite acceder a los alumnos de un curso.
	//WHERE c.docente.id filtra los cursos donde el ID del profesor (docente.id) es igual al pasado como parámetro (docenteId).
	// AND :fechaActual BETWEEN c.fechaInicio AND c.fechaFin: También se asegura de que la fecha actual (fechaActual) esté dentro del rango de fechas de inicio y fin del curso.
		//findAlumnosByCursosVigentes: Este método devuelve una lista de alumnos (List<Alumno>) que están inscritos en cursos vigentes (cuya fecha está entre la fecha de inicio y fin) 
		//y cuyo profesor tiene el ID proporcionado.
	
	@Query("SELECT a FROM Curso c JOIN c.alumnos a WHERE c.docente.id = :docenteId AND :fechaActual BETWEEN c.fechaInicio AND c.fechaFin")
    List<Alumno> findAlumnosByCursosVigentes(@Param("docenteId") Long docenteId, @Param("fechaActual") LocalDate fechaActual);
}
