
package net.t6.olmedorest.services;

import net.t6.olmedorest.entities.Publicacion;
import net.t6.olmedorest.exceptions.RecordNotFoundException;
import net.t6.olmedorest.repositories.PublicacionRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

	@Autowired
	PublicacionRepository repo;

	public List<Publicacion> getAll(){
		List<Publicacion> publicacionList = repo.findAll();
		if(publicacionList.size() > 0) {
			return publicacionList;
		} else {
			return new ArrayList<Publicacion>();
		}
	}
     		
	public Publicacion findById(Long id) throws RecordNotFoundException{
		Optional<Publicacion> publicacion = repo.findById(id);
		if(publicacion.isPresent()) {
			return publicacion.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public List<Publicacion> findByNombreContaining(String nombre){
		List<Publicacion> publicacionList = repo.findByNombreContaining(nombre);
		if(publicacionList.size() > 0) {
			return publicacionList;
		} else {
			return new ArrayList<Publicacion>();
		}
	}
	
	public Publicacion createPublicacion(Publicacion publicacion){
		return repo.save(publicacion);
	}

	public Publicacion updatePublicacion(Publicacion publicacion) throws RecordNotFoundException {
		Optional<Publicacion> publicacionTemp = repo.findById(publicacion.getId());
	
		if(publicacionTemp.isPresent()){
			return repo.save(publicacion);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deletePublicacionById(Long id) throws RecordNotFoundException{
		Optional<Publicacion> publicacion = repo.findById(id);
		if(publicacion.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
