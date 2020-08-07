
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.services.UsuarioService;
import net.t6.olmedorest.entities.Publicacion;
import net.t6.olmedorest.entities.Usuario;
import net.t6.olmedorest.entities.login;
import net.t6.olmedorest.exceptions.RecordNotFoundException;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.UUID;

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
	
	@GetMapping("/usuario/login/{correo}/{contrasenia}")
	public ResponseEntity<List<login>> getUsuarioByEmailPass(@PathVariable("correo") String correo, @PathVariable("contrasenia") String contrasenia){
		List<login> entity = service.findByCorreoAndContrasenia(correo, contrasenia);
		return new ResponseEntity<List<login>>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/usuario/findbynombreUsuario/{nombreUsuario}")
	public ResponseEntity<List<Usuario>> getByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
		List<Usuario> list = service.findByNombreUsuarioContaining(nombreUsuario);
		return new ResponseEntity<List<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
	}				
	
	@GetMapping("/usuario/findByEmail/{correo}")
	public ResponseEntity<List<login>> getEmailContra(@PathVariable("correo") String correo){
		List<login> list = service.findByCorreoContaining(correo);
		return new ResponseEntity<List<login>>(list, new HttpHeaders(), HttpStatus.OK);	
	}			

	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> createUsuario(@RequestParam("usuario") String s)throws JsonMappingException, JsonProcessingException{
		ObjectMapper om = new ObjectMapper();
		Usuario usuario=om.readValue(s, Usuario[].class)[0];		
		
		service.createUsuario(usuario);
		return new ResponseEntity<Usuario>(usuario, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/usuario")
	public ResponseEntity<Usuario> updateUsuario(@RequestParam("usuario") String s) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		ObjectMapper om = new ObjectMapper();
		Usuario usuario = om.readValue(s, Usuario[].class)[0];
		service.updateUsuario(usuario);
		return new ResponseEntity<Usuario>(usuario, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/usuario/{id}")
	public HttpStatus deleteUsuarioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteUsuarioById(id);
		return HttpStatus.OK;
	}
}				
