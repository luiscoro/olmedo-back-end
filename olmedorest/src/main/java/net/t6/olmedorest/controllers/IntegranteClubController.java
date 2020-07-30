
package net.t6.olmedorest.controllers;

import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.IntegranteClub;
import net.t6.olmedorest.img.PictureService;
import net.t6.olmedorest.services.IntegranteClubService;

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
public class IntegranteClubController {
	@Autowired
	IntegranteClubService service;
	
	@Autowired
	PictureService picService;
	
	@Value("${upload.path}")
	public String uploadDir;
	
	@GetMapping("/integranteClub")
	public ResponseEntity<List<IntegranteClub>> getAll() {
		List<IntegranteClub> list = service.getAll();
		return new ResponseEntity<List<IntegranteClub>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/integranteClub/{id}")
	public ResponseEntity<IntegranteClub> getIntegranteClubById(@PathVariable("id") Long id) throws RecordNotFoundException {
		IntegranteClub entity = service.findById(id);
		return new ResponseEntity<IntegranteClub>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/integranteClub/findbyperfil/{perfil}")
	public ResponseEntity<List<IntegranteClub>> getByPerfil(@PathVariable("perfil") String perfil){
		List<IntegranteClub> list = service.findByPerfilContaining(perfil);
		return new ResponseEntity<List<IntegranteClub>>(list, new HttpHeaders(), HttpStatus.OK);
	}				
	
	@GetMapping("/integranteClub/pic/{id}")
    public void getPhotoByID(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {    	
    	Path p = Paths.get(uploadDir + File.separator + id.toString()+".jpg");
    	System.out.println(p);
    	InputStream is = new FileInputStream(p.toFile());
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

	@PostMapping("/integranteClub")
	public ResponseEntity<IntegranteClub> createIntegranteClub(@RequestParam("integranteClub") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		IntegranteClub integranteClub=om.readValue(s, IntegranteClub[].class)[0];		
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			integranteClub.setFoto(idPic);
		}
		if(service.existePorNombre(integranteClub.getNombre()))
			return new ResponseEntity<IntegranteClub>(integranteClub, new HttpHeaders(),HttpStatus.CONFLICT);
		service.createIntegranteClub(integranteClub);
		return new ResponseEntity<IntegranteClub>(integranteClub, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/integranteClub")
	public ResponseEntity<IntegranteClub> updateIntegranteClub(@RequestParam("integranteClub") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		IntegranteClub integranteClub=om.readValue(s, IntegranteClub[].class)[0];		
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			integranteClub.setFoto(idPic);
		}
		service.updateIntegranteClub(integranteClub);
		return new ResponseEntity<IntegranteClub>(integranteClub, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/integranteClub/{id}")
	public HttpStatus deleteIntegranteClubById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteIntegranteClubById(id);
		return HttpStatus.OK;
	}
}				
