package com.BI.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.BI.entities.virus;
import com.BI.service.VirusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/virus")
@Api(value = "REST información de estudiantes")
public class VirusController {

	@Autowired
	private VirusService virusService;

	@ApiOperation("Lista de virus")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<virus>> fetchStudents() {
		try {
			List<virus> students = new ArrayList<>();
			students = virusService.findAll();
			return new ResponseEntity<List<virus>>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<virus>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Obtener virus por id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<virus> fetchStudent(@PathVariable("id") Integer id) {

		try {
			Optional<virus> student = virusService.findById(id);

			if (!student.isPresent()) {
				return new ResponseEntity<virus>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<virus>(student.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<virus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Guardar virus")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Valid @RequestBody virus student) {
		try {
			virus s = new virus();
			s = virusService.save(student);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(s.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation("Actualizar virus")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody virus student) {
		try {
			virusService.update(student);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation("Borrar virus")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		try {
			Optional<virus> student = virusService.findById(id);

			if (!student.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				virusService.deleteById(id);
				return new ResponseEntity<>("Signo se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@ApiOperation("Obtener virus por nombre")
	@GetMapping(value = "/name/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<virus> fetchLikeQuestionsById(@PathVariable("nombre") String nombre) {

		try {
			Optional<virus> like = virusService.fetchVirusByName(nombre);
			if (!like.isPresent()) {
				return new ResponseEntity<virus>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<virus>(like.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<virus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
