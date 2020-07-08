package com.BI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BI.entities.virus;
import com.BI.repository.VirusRepository;
import com.BI.service.VirusService;

@Service
public class VirusServiceImpl implements VirusService {

	@Autowired
	private VirusRepository virusRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<virus> findAll() throws Exception {
		return virusRepository.findAll();
	}

	@Transactional
	@Override
	public virus save(virus t) throws Exception {
		return virusRepository.save(t);
	}
	
	@Transactional
	@Override
	public virus update(virus t) throws Exception {
		return virusRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<virus> findById(Integer id) throws Exception {
		return virusRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		virusRepository.deleteById(id);

	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<virus> fetchVirusByName(String nombre) throws Exception {
		return virusRepository.fetchVirusByName(nombre);
	}
}
