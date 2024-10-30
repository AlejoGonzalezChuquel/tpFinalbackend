package com.API.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.TP.entities.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

}
