
package net.t6.olmedorest.controllers;
import net.t6.olmedorest.entities.UsuarioServicio;
import net.t6.olmedorest.exceptions.RecordNotFoundException;
import net.t6.olmedorest.img.PictureService;
import net.t6.olmedorest.services.UsuarioServicioService;
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
public class UsuarioServicioController {
	@Autowired
	UsuarioServicioService service;
	
	@Autowired
	PictureService picService;
	
	@Value("${upload.path}")
	public String uploadDir;
	
	@GetMapping("/usuarioServicio")
	public ResponseEntity<List<UsuarioServicio>> getAll() {
		List<UsuarioServicio> list = service.getAll();
		return new ResponseEntity<List<UsuarioServicio>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/usuarioServicio/{id}")
	public ResponseEntity<UsuarioServicio> getUsuarioServicioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		UsuarioServicio entity = service.findById(id);
		return new ResponseEntity<UsuarioServicio>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/pic/{id}")
    public void getPhotoByID(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {    	
    	Path p = Paths.get(uploadDir + File.separator + id.toString()+".jpg");
    	System.out.println(p);
    	InputStream is = new FileInputStream(p.toFile());
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

	@PostMapping("/usuarioServicio")
	public ResponseEntity<UsuarioServicio> createUsuarioServicio(@RequestParam("usuarioServicio") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		UsuarioServicio usuarioServicio=om.readValue(s, UsuarioServicio[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			usuarioServicio.setArchivoSolicitud(idPic);
		}
		
		service.createUsuarioServicio(usuarioServicio);
		return new ResponseEntity<UsuarioServicio>(usuarioServicio, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/usuarioServicio")
	public ResponseEntity<UsuarioServicio> updateUsuarioServicio(@RequestParam("usuarioServicio") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		UsuarioServicio usuarioServicio=om.readValue(s, UsuarioServicio[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			usuarioServicio.setArchivoSolicitud(idPic);
		}
		
		service.updateUsuarioServicio(usuarioServicio);
		return new ResponseEntity<UsuarioServicio>(usuarioServicio, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/usuarioServicio/{id}")
	public HttpStatus deleteUsuarioServicioById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteUsuarioServicioById(id);
		return HttpStatus.OK;
	}
}				
