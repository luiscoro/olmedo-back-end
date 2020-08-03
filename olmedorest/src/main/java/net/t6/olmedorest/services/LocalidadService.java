
package net.t6.olmedorest.services;

import net.t6.olmedorest.entities.Localidad;
import net.t6.olmedorest.entities.Usuario;
import net.t6.olmedorest.exceptions.RecordNotFoundException;
import net.t6.olmedorest.repositories.LocalidadRepository;
import net.t6.olmedorest.repositories.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class LocalidadService {

	@Autowired
	UsuarioRepository repou;
	
	@Autowired
	LocalidadRepository repo;

	public List<Localidad> getAll(){
		List<Localidad> localidadList = repo.findAll();
		if(localidadList.size() > 0) {
			return localidadList;
		} else {
			return new ArrayList<Localidad>();
		}
	}
     		
	public Localidad findById(Long id) throws RecordNotFoundException{
		Optional<Localidad> localidad = repo.findById(id);
		if(localidad.isPresent()) {
			return localidad.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public Localidad createLocalidad(Localidad localidad){
		repo.save(localidad);
		
		Iterator<Usuario> itr = localidad.getUsuarios().iterator(); 
		while(itr.hasNext()){ 
			Usuario u = (Usuario)itr.next();
			repou.save( new Usuario(u.getNombreUsuario(),u.getCorreo(),u.getContrasenia(),u.isHabilitado(),
					u.getNombreCompleto(),u.getSexo(),u.getFechaNacimiento(),
					u.getTelefono(),u.getDireccion(),u.getPuntajeBeneficio(),
					u.getFechaCreacion(), localidad));
		}		
		return localidad;
	}
	
	public Localidad updateLocalidad(Localidad localidad) throws RecordNotFoundException {
		Optional<Localidad> localidadTemp = repo.findById(localidad.getId());
	
		if(localidadTemp.isPresent()){
			repo.save(localidad);
			
			Iterator<Usuario> itr = localidad.getUsuarios().iterator(); 
			while(itr.hasNext()){ 
				Usuario u = (Usuario)itr.next();
				repou.save( new Usuario(u.getNombreUsuario(),u.getCorreo(),u.getContrasenia(),u.isHabilitado(),
						u.getNombreCompleto(),u.getSexo(),u.getFechaNacimiento(),
						u.getTelefono(),u.getDireccion(),u.getPuntajeBeneficio(),
						u.getFechaCreacion(), localidad));
			}	
			
			return localidad;
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteLocalidadById(Long id) throws RecordNotFoundException{
		Optional<Localidad> localidad = repo.findById(id);
		if(localidad.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
