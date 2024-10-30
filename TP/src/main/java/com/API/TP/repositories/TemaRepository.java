package com.API.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.TP.entities.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

}
