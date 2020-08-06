
package net.t6.olmedorest.services;

import net.t6.olmedorest.repositories.ServicioRepository;
import net.t6.olmedorest.repositories.TipoServicioRepository;
import net.t6.olmedorest.entities.TipoServicio;
import net.t6.olmedorest.exceptions.RecordNotFoundException;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioService {

	@Autowired
	TipoServicioRepository repo;
	
	@Autowired
	ServicioRepository repos;

	public List<TipoServicio> getAll(){
		List<TipoServicio> tipoServicioList = repo.findAll();
		if(tipoServicioList.size() > 0) {
			return tipoServicioList;
		} else {
			return new ArrayList<TipoServicio>();
		}
	}
     		
	public TipoServicio findById(Long id) throws RecordNotFoundException{
		Optional<TipoServicio> tipoServicio = repo.findById(id);
		if(tipoServicio.isPresent()) {
			return tipoServicio.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public TipoServicio createTipoServicio(TipoServicio tipoServicio){
		return repo.save(tipoServicio);
		
		/*Iterator<Servicio> itr = tipoServicio.getServicios().iterator(); 
		while(itr.hasNext()){ 
			Servicio s = (Servicio)itr.next();
			repos.save( new Servicio(s.getNombre(),s.getDetalle(),s.getFoto(),
					s.getEstado(),s.getValor(),s.getDescuento(), tipoServicio));
		}	
		return tipoServicio;*/
	}

	public TipoServicio updateTipoServicio(TipoServicio tipoServicio) throws RecordNotFoundException {
		Optional<TipoServicio> tipoServicioTemp = repo.findById(tipoServicio.getId());
	
		if(tipoServicioTemp.isPresent()){
			return repo.save(tipoServicio);
			
			/*Iterator<Servicio> itr = tipoServicio.getServicios().iterator(); 
			while(itr.hasNext()){ 
				Servicio s = (Servicio)itr.next();
				repos.save( new Servicio(s.getNombre(),s.getDetalle(),s.getFoto(),
						s.getEstado(),s.getValor(),s.getDescuento(), tipoServicio));
			}	
			return tipoServicio;*/
			
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteTipoServicioById(Long id) throws RecordNotFoundException{
		Optional<TipoServicio> tipoServicio = repo.findById(id);
		if(tipoServicio.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
