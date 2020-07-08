package com.BI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BI.entities.virus;

@Repository
public interface VirusRepository extends JpaRepository<virus, Integer> {
	
	@Query("select l from virus l where l.nombre.id =?1")
	Optional<virus> fetchVirusByName(String nombre);
	
}
