
package net.t6.olmedorest.services;

import net.t6.olmedorest.repositories.ServicioRepository;
import net.t6.olmedorest.repositories.UsuarioRepository;
import net.t6.olmedorest.repositories.UsuarioServicioRepository;
import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.Servicio;
import net.t6.olmedorest.entities.Usuario;
import net.t6.olmedorest.entities.UsuarioServicio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

	@Autowired
	ServicioRepository repo;
	
	@Autowired
	UsuarioRepository repou;
	
	@Autowired
	UsuarioServicioRepository repous;

	public List<Servicio> getAll(){
		List<Servicio> servicioList = repo.findAll();
		if(servicioList.size() > 0) {
			return servicioList;
		} else {
			return new ArrayList<Servicio>();
		}
	}
     		
	public Servicio findById(Long id) throws RecordNotFoundException{
		Optional<Servicio> servicio = repo.findById(id);
		if(servicio.isPresent()) {
			return servicio.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public List<Servicio> findByNombreContaining(String nombre){
		List<Servicio> servicioList = repo.findByNombreContaining(nombre);
		if(servicioList.size() > 0) {
			return servicioList;
		} else {
			return new ArrayList<Servicio>();
		}
	}
	
	public List<Servicio> findByEstadoContaining(String estado){
		List<Servicio> servicioList = repo.findByEstadoContaining(estado);
		if(servicioList.size() > 0) {
			return servicioList;
		} else {
			return new ArrayList<Servicio>();
		}
	}
	
	public Servicio createServicio(Servicio servicio){
		repo.save(servicio);
		
		Iterator<UsuarioServicio> itr1 = servicio.getUsuarioServicios().iterator(); 
		while(itr1.hasNext()){ 
			UsuarioServicio us = (UsuarioServicio)itr1.next();
			repous.save(new UsuarioServicio(us.getFecha(),us.getArchivoSolicitud(),us.getNombrePromocion(),
					us.getDetallePromocion(),repou.save(new Usuario()),servicio));
		}	
        
        return servicio;
	}

	public Servicio updateServicio(Servicio servicio) throws RecordNotFoundException {
		Optional<Servicio> servicioTemp = repo.findById(servicio.getId());
	
		if(servicioTemp.isPresent()){
			repo.save(servicio);
			
			Iterator<UsuarioServicio> itr1 = servicio.getUsuarioServicios().iterator(); 
			while(itr1.hasNext()){ 
				UsuarioServicio us = (UsuarioServicio)itr1.next();
				repous.save(new UsuarioServicio(us.getFecha(),us.getArchivoSolicitud(),us.getNombrePromocion(),
						us.getDetallePromocion(),repou.save(new Usuario()),servicio));
			}	
	        
	        return servicio;
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteServicioById(Long id) throws RecordNotFoundException{
		Optional<Servicio> servicio = repo.findById(id);
		if(servicio.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
