package com.BI.service;

import java.util.Optional;

import com.BI.entities.virus;

public interface VirusService extends CrudService<virus> {
	
	Optional<virus> fetchVirusByName(String nombre) throws Exception;

}
