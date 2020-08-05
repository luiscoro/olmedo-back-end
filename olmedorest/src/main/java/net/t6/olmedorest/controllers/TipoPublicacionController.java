
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.services.TipoPublicacionService;
import net.t6.olmedorest.entities.TipoPublicacion;
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
public class TipoPublicacionController {
	@Autowired
	TipoPublicacionService service;
	
	@GetMapping("/tipoPublicacion")
	public ResponseEntity<List<TipoPublicacion>> getAll() {
		List<TipoPublicacion> list = service.getAll();
		return new ResponseEntity<List<TipoPublicacion>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/tipoPublicacion/{id}")
	public ResponseEntity<TipoPublicacion> getTipoPublicacionById(@PathVariable("id") Long id) throws RecordNotFoundException {
		TipoPublicacion entity = service.findById(id);
		return new ResponseEntity<TipoPublicacion>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/tipoPublicacion")
	public ResponseEntity<TipoPublicacion> createTipoPublicacion(@RequestBody TipoPublicacion tipoPublicacion){
		
	/*	if(service.existePorDetalle(tipoPublicacion.getDetalle()))
			return new ResponseEntity<TipoPublicacion>(tipoPublicacion, new HttpHeaders(),HttpStatus.CONFLICT);*/
		service.createTipoPublicacion(tipoPublicacion);
		return new ResponseEntity<TipoPublicacion>(tipoPublicacion, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/tipoPublicacion")
	public ResponseEntity<TipoPublicacion> updateTipoPublicacion(@RequestBody TipoPublicacion tipoPublicacion) throws RecordNotFoundException{
		service.updateTipoPublicacion(tipoPublicacion);
		return new ResponseEntity<TipoPublicacion>(tipoPublicacion, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/tipoPublicacion/{id}")
	public HttpStatus deleteTipoPublicacionById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteTipoPublicacionById(id);
		return HttpStatus.OK;
	}
}				
