
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.entities.Pago;
import net.t6.olmedorest.exceptions.RecordNotFoundException;
import net.t6.olmedorest.img.PictureService;
import net.t6.olmedorest.services.PagoService;

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
public class PagoController {
	@Autowired
	PagoService service;
	
	@Autowired
	PictureService picService;
	
	@Value("${upload.path}")
	public String uploadDir;
	
	@GetMapping("/pago")
	public ResponseEntity<List<Pago>> getAll() {
		List<Pago> list = service.getAll();
		return new ResponseEntity<List<Pago>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/pago/{id}")
	public ResponseEntity<Pago> getPagoById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Pago entity = service.findById(id);
		return new ResponseEntity<Pago>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/pago/findbyestado/{estado}")
	public ResponseEntity<List<Pago>> getByEstado(@PathVariable("estado") String estado){
		List<Pago> list = service.findByEstadoContaining(estado);
		return new ResponseEntity<List<Pago>>(list, new HttpHeaders(), HttpStatus.OK);
	}				

	@GetMapping("/pago/pic/{id}")
    public void getPhotoByID(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {    	
    	Path p = Paths.get(uploadDir + File.separator + id.toString()+".jpg");
    	System.out.println(p);
    	InputStream is = new FileInputStream(p.toFile());
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

	@PostMapping("/pago")
	public ResponseEntity<Pago> createPago(@RequestParam("pago") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Pago pago=om.readValue(s, Pago[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			pago.setFoto(idPic);
		}
		
		service.createPago(pago);
		return new ResponseEntity<Pago>(pago, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/pago")
	public ResponseEntity<Pago> updatePago(@RequestParam("pago") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Pago pago=om.readValue(s, Pago[].class)[0];
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			pago.setFoto(idPic);
		}
		
		service.updatePago(pago);
		return new ResponseEntity<Pago>(pago, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/pago/{id}")
	public HttpStatus deletePagoById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deletePagoById(id);
		return HttpStatus.OK;
	}
}				
