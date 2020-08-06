
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.services.TipoServicioService;
import net.t6.olmedorest.entities.TipoServicio;
import net.t6.olmedorest.exceptions.RecordNotFoundException;

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
public class TipoServicioController {
	@Autowired
	TipoServicioService service;
	
	@GetMapping("/tipoServicio")
	public ResponseEntity<List<TipoServicio>> getAll() {
		List<TipoServicio> list = service.getAll();
		return new ResponseEntity<List<TipoServicio>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/tipoServicio/{id}")
	public ResponseEntity<TipoServicio> getTipoServicioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		TipoServicio entity = service.findById(id);
		return new ResponseEntity<TipoServicio>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/tipoServicio")
	public ResponseEntity<TipoServicio> createTipoServicio(@RequestBody TipoServicio tipoServicio){
		service.createTipoServicio(tipoServicio);
		return new ResponseEntity<TipoServicio>(tipoServicio, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/tipoServicio")
	public ResponseEntity<TipoServicio> updateTipoServicio(@RequestBody TipoServicio tipoServicio) throws RecordNotFoundException{
		service.updateTipoServicio(tipoServicio);
		return new ResponseEntity<TipoServicio>(tipoServicio, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/tipoServicio/{id}")
	public HttpStatus deleteTipoServicioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteTipoServicioById(id);
		return HttpStatus.OK;
	}
}				
