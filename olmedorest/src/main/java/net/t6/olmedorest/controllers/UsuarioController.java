
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.services.UsuarioService;
import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.Usuario;

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
public class UsuarioController {
	@Autowired
	UsuarioService service;
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> list = service.getAll();
		return new ResponseEntity<List<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Usuario entity = service.findById(id);
		return new ResponseEntity<Usuario>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/usuario/findbynombreUsuario/{nombreUsuario}")
	public ResponseEntity<List<Usuario>> getByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
		List<Usuario> list = service.findByNombreUsuarioContaining(nombreUsuario);
		return new ResponseEntity<List<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
	}				
	@GetMapping("/usuario/findbycorreo/{correo}")
	public ResponseEntity<List<Usuario>> getByCorreo(@PathVariable("correo") String correo){
		List<Usuario> list = service.findByCorreoContaining(correo);
		return new ResponseEntity<List<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
	}				

	@PostMapping("/usuario")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
		
		if(service.existePorNombreUsuario(usuario.getNombreUsuario()))
			return new ResponseEntity<Usuario>(usuario, new HttpHeaders(),HttpStatus.CONFLICT);
		if(service.existePorCorreo(usuario.getCorreo()))
			return new ResponseEntity<Usuario>(usuario, new HttpHeaders(),HttpStatus.CONFLICT);
		if(service.existePorNombreCompleto(usuario.getNombreCompleto()))
			return new ResponseEntity<Usuario>(usuario, new HttpHeaders(),HttpStatus.CONFLICT);
		service.createUsuario(usuario);
		return new ResponseEntity<Usuario>(usuario, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/usuario")
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) throws RecordNotFoundException{
		service.updateUsuario(usuario);
		return new ResponseEntity<Usuario>(usuario, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/usuario/{id}")
	public HttpStatus deleteUsuarioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteUsuarioById(id);
		return HttpStatus.OK;
	}
}				
