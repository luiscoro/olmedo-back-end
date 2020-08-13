
package net.t6.olmedorest.services;

import net.t6.olmedorest.entities.Pago;
import net.t6.olmedorest.entities.UsuarioServicio;
import net.t6.olmedorest.exceptions.RecordNotFoundException;
import net.t6.olmedorest.repositories.PagoRepository;
import net.t6.olmedorest.repositories.UsuarioServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioService {

	@Autowired
	UsuarioServicioRepository repo;
	
	@Autowired
	PagoRepository repop;

	public List<UsuarioServicio> getAll(){
		List<UsuarioServicio> usuarioServicioList = repo.findAll();
		if(usuarioServicioList.size() > 0) {
			return usuarioServicioList;
		} else {
			return new ArrayList<UsuarioServicio>();
		}
	}
     		
	public UsuarioServicio findById(Long id) throws RecordNotFoundException{
		Optional<UsuarioServicio> usuarioServicio = repo.findById(id);
		if(usuarioServicio.isPresent()) {
			return usuarioServicio.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public UsuarioServicio createUsuarioServicio(UsuarioServicio usuarioServicio){
		return repo.save(usuarioServicio);
	}

	public UsuarioServicio updateUsuarioServicio(UsuarioServicio usuarioServicio) throws RecordNotFoundException {
		Optional<UsuarioServicio> usuarioServicioTemp = repo.findById(usuarioServicio.getId());
	
		if(usuarioServicioTemp.isPresent()){
			return repo.save(usuarioServicio);
			
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteUsuarioServicioById(Long id) throws RecordNotFoundException{
		Optional<UsuarioServicio> usuarioServicio = repo.findById(id);
		if(usuarioServicio.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
