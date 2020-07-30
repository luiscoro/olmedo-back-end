
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.Localidad;
import net.t6.olmedorest.services.LocalidadService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;	
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LocalidadController {
	@Autowired
	LocalidadService service;
	
	@GetMapping("/localidad")
	public ResponseEntity<List<Localidad>> getAll() {
		List<Localidad> list = service.getAll();
		return new ResponseEntity<List<Localidad>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/localidad/{id}")
	public ResponseEntity<Localidad> getLocalidadById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Localidad entity = service.findById(id);
		return new ResponseEntity<Localidad>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/localidad")
	public ResponseEntity<Localidad> createLocalidad(@RequestBody Localidad localidad){
		service.createLocalidad(localidad);
		return new ResponseEntity<Localidad>(localidad, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/localidad")
	public ResponseEntity<Localidad> updateLocalidad(@RequestBody Localidad localidad) throws RecordNotFoundException{
		service.updateLocalidad(localidad);
		return new ResponseEntity<Localidad>(localidad, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/localidad/{id}")
	public HttpStatus deleteLocalidadById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteLocalidadById(id);
		return HttpStatus.OK;
	}
}				
