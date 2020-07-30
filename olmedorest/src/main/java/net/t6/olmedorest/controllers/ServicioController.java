
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.services.ServicioService;
import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.Servicio;
import net.t6.olmedorest.img.PictureService;

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
public class ServicioController {
	@Autowired
	ServicioService service;
	
	@Autowired
	PictureService picService;
	
	@Value("${upload.path}")
	public String uploadDir;
	
	@GetMapping("/servicio")
	public ResponseEntity<List<Servicio>> getAll() {
		List<Servicio> list = service.getAll();
		return new ResponseEntity<List<Servicio>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/servicio/{id}")
	public ResponseEntity<Servicio> getServicioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Servicio entity = service.findById(id);
		return new ResponseEntity<Servicio>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/servicio/findbynombre/{nombre}")
	public ResponseEntity<List<Servicio>> getByNombre(@PathVariable("nombre") String nombre){
		List<Servicio> list = service.findByNombreContaining(nombre);
		return new ResponseEntity<List<Servicio>>(list, new HttpHeaders(), HttpStatus.OK);
	}				
	@GetMapping("/servicio/findbyestado/{estado}")
	public ResponseEntity<List<Servicio>> getByEstado(@PathVariable("estado") String estado){
		List<Servicio> list = service.findByEstadoContaining(estado);
		return new ResponseEntity<List<Servicio>>(list, new HttpHeaders(), HttpStatus.OK);
	}				

	@GetMapping("/servicio/pic/{id}")
    public void getPhotoByID(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {    	
    	Path p = Paths.get(uploadDir + File.separator + id.toString()+".jpg");
    	System.out.println(p);
    	InputStream is = new FileInputStream(p.toFile());
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

	@PostMapping("/servicio")
	public ResponseEntity<Servicio> createServicio(@RequestParam("servicio") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Servicio servicio=om.readValue(s, Servicio[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			servicio.setFoto(idPic);
		}
		
		service.createServicio(servicio);
		return new ResponseEntity<Servicio>(servicio, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/servicio")
	public ResponseEntity<Servicio> updateServicio(@RequestParam("servicio") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Servicio servicio=om.readValue(s, Servicio[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			servicio.setFoto(idPic);
		}
		
		service.updateServicio(servicio);
		return new ResponseEntity<Servicio>(servicio, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/servicio/{id}")
	public HttpStatus deleteServicioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteServicioById(id);
		return HttpStatus.OK;
	}
}				
