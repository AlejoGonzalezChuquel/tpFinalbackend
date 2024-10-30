package com.API.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.TP.entities.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,Long> {

}
