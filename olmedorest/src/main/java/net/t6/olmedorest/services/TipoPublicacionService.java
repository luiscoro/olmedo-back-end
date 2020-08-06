
package net.t6.olmedorest.services;

import net.t6.olmedorest.repositories.PublicacionRepository;
import net.t6.olmedorest.repositories.TipoPublicacionRepository;
import net.t6.olmedorest.entities.TipoPublicacion;
import net.t6.olmedorest.exceptions.RecordNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoPublicacionService {

	@Autowired
	PublicacionRepository repop;
	
	@Autowired
	TipoPublicacionRepository repo;
	

	public List<TipoPublicacion> getAll(){
		List<TipoPublicacion> tipoPublicacionList = repo.findAll();
		if(tipoPublicacionList.size() > 0) {
			return tipoPublicacionList;
		} else {
			return new ArrayList<TipoPublicacion>();
		}
	}
     		
	public TipoPublicacion findById(Long id) throws RecordNotFoundException{
		Optional<TipoPublicacion> tipoPublicacion = repo.findById(id);
		if(tipoPublicacion.isPresent()) {
			return tipoPublicacion.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public TipoPublicacion createTipoPublicacion(TipoPublicacion tipoPublicacion){
		return repo.save(tipoPublicacion);
		/*
		Iterator<Publicacion> itr = tipoPublicacion.getPublicaciones().iterator(); 
		while(itr.hasNext()){ 
			Publicacion p = (Publicacion)itr.next();
			repop.save(new Publicacion(p.getNombre(),p.getDetalle(),p.getAutor(),p.getFecha(),
					p.getFoto(), tipoPublicacion));
		}		
		return tipoPublicacion;*/
		
	}

	
	public TipoPublicacion updateTipoPublicacion(TipoPublicacion tipoPublicacion) throws RecordNotFoundException {
		Optional<TipoPublicacion> tipoPublicacionTemp = repo.findById(tipoPublicacion.getId());	
		
		if(tipoPublicacionTemp.isPresent()){		
			return repo.save(tipoPublicacion);
			/*
			Iterator<Publicacion> itr = tipoPublicacion.getPublicaciones().iterator(); 
			while(itr.hasNext()){ 
				Publicacion p = (Publicacion)itr.next();
				repop.save(new Publicacion(p.getNombre(),p.getDetalle(),p.getAutor(),p.getFecha(),
						p.getFoto(), tipoPublicacion));}*/

		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteTipoPublicacionById(Long id) throws RecordNotFoundException{
		Optional<TipoPublicacion> tipoPublicacion = repo.findById(id);
		if(tipoPublicacion.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		
	
	public boolean existePorDetalle(String detalle){
        return repo.existsByDetalle(detalle);
    }

}
