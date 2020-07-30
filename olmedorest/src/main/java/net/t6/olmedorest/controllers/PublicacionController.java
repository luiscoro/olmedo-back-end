
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.Publicacion;
import net.t6.olmedorest.img.PictureService;
import net.t6.olmedorest.services.PublicacionService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PublicacionController {
	@Autowired
	PublicacionService service;
	
	@Autowired
	PictureService picService;
	
	@Value("${upload.path}")
	public String uploadDir;
	
	@GetMapping("/publicacion")
	public ResponseEntity<List<Publicacion>> getAll() {
		List<Publicacion> list = service.getAll();
		return new ResponseEntity<List<Publicacion>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/publicacion/{id}")
	public ResponseEntity<Publicacion> getPublicacionById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Publicacion entity = service.findById(id);
		return new ResponseEntity<Publicacion>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/publicacion/findbynombre/{nombre}")
	public ResponseEntity<List<Publicacion>> getByNombre(@PathVariable("nombre") String nombre){
		List<Publicacion> list = service.findByNombreContaining(nombre);
		return new ResponseEntity<List<Publicacion>>(list, new HttpHeaders(), HttpStatus.OK);
	}				

	@GetMapping("/publicacion/pic/{id}")
    public void getPhotoByID(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {    	
    	Path p = Paths.get(uploadDir + File.separator + id.toString()+".jpg");
    	System.out.println(p);
    	InputStream is = new FileInputStream(p.toFile());
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

	@PostMapping("/publicacion")
	public ResponseEntity<Publicacion> createPublicacion(@RequestParam("publicacion") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Publicacion publicacion=om.readValue(s, Publicacion[].class)[0];		
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			publicacion.setFoto(idPic);
		}
		service.createPublicacion(publicacion);
		return new ResponseEntity<Publicacion>(publicacion, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/publicacion")
	public ResponseEntity<Publicacion> updatePublicacion(@RequestParam("publicacion") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Publicacion publicacion=om.readValue(s, Publicacion[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			publicacion.setFoto(idPic);
		}
		
		service.updatePublicacion(publicacion);
		return new ResponseEntity<Publicacion>(publicacion, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/publicacion/{id}")
	public HttpStatus deletePublicacionById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deletePublicacionById(id);
		return HttpStatus.OK;
	}
}				
