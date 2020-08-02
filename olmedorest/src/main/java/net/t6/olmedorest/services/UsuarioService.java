
package net.t6.olmedorest.services;

import net.t6.olmedorest.repositories.NotificacionRepository;
import net.t6.olmedorest.repositories.ServicioRepository;
import net.t6.olmedorest.repositories.UsuarioRepository;
import net.t6.olmedorest.repositories.UsuarioServicioRepository;
import net.t6.olmedorest.entities.Notificacion;
import net.t6.olmedorest.entities.Servicio;
import net.t6.olmedorest.entities.Usuario;
import net.t6.olmedorest.entities.UsuarioServicio;
import net.t6.olmedorest.exceptions.RecordNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	
	@Autowired
	NotificacionRepository repon;
	
	@Autowired
	UsuarioRepository repo;
	
	@Autowired
	UsuarioServicioRepository repous;
	
	@Autowired
	ServicioRepository repos;
	

	public List<Usuario> getAll(){
		List<Usuario> usuarioList = repo.findAll();
		if(usuarioList.size() > 0) {
			return usuarioList;
		} else {
			return new ArrayList<Usuario>();
		}
	}
     		
	public Usuario findById(Long id) throws RecordNotFoundException{
		Optional<Usuario> usuario = repo.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public List<Usuario> findByNombreUsuarioContaining(String nombreUsuario){
		List<Usuario> usuarioList = repo.findByNombreUsuarioContaining(nombreUsuario);
		if(usuarioList.size() > 0) {
			return usuarioList;
		} else {
			return new ArrayList<Usuario>();
		}
	}
	
	public List<Usuario> findByCorreoContaining(String correo){
		List<Usuario> usuarioList = repo.findByCorreoContaining(correo);
		if(usuarioList.size() > 0) {
			return usuarioList;
		} else {
			return new ArrayList<Usuario>();
		}
	}
	
	public Usuario createUsuario(Usuario usuario){
		
        repo.save(usuario);
        
		Iterator<Notificacion> itr = usuario.getNotificaciones().iterator(); 
		while(itr.hasNext()){ 
			Notificacion n = (Notificacion)itr.next();
			repon.save( new Notificacion(n.getNombre(),n.getDetalle(),
					n.getEstado(), usuario));
		}		
		
		Iterator<UsuarioServicio> itr1 = usuario.getUsuarioServicios().iterator(); 
		while(itr1.hasNext()){ 
			UsuarioServicio us = (UsuarioServicio)itr1.next();
			repous.save( new UsuarioServicio(us.getFecha(),us.getArchivoSolicitud(),us.getNombrePromocion(),
					us.getDetallePromocion(),usuario,repos.save(new Servicio())));
		}	
        
        return usuario;
	}

	public Usuario updateUsuario(Usuario usuario) throws RecordNotFoundException {
		Optional<Usuario> usuarioTemp = repo.findById(usuario.getId());
	
		if(usuarioTemp.isPresent()){
			repo.save(usuario);
	        
			Iterator<Notificacion> itr = usuario.getNotificaciones().iterator(); 
			while(itr.hasNext()){ 
				Notificacion n = (Notificacion)itr.next();
				repon.save( new Notificacion(n.getNombre(),n.getDetalle(),
						n.getEstado(), usuario));
			}		
			
			Iterator<UsuarioServicio> itr1 = usuario.getUsuarioServicios().iterator(); 
			while(itr1.hasNext()){ 
				UsuarioServicio us = (UsuarioServicio)itr1.next();
				repous.save( new UsuarioServicio(us.getFecha(),us.getArchivoSolicitud(),us.getNombrePromocion(),
						us.getDetallePromocion(),usuario,repos.save(new Servicio())));
			}	
	        
	        return usuario;
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteUsuarioById(Long id) throws RecordNotFoundException{
		Optional<Usuario> usuario = repo.findById(id);
		if(usuario.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		
	
	public boolean existePorNombreUsuario(String nombreUsuario){
        return repo.existsByNombreUsuario(nombreUsuario);       
    }
	
	public boolean existePorCorreo(String correo){
        return repo.existsByCorreo(correo);       
    }

	public boolean existePorNombreCompleto(String nombreCompleto){
        return repo.existsByNombreCompleto(nombreCompleto);       
    }
}
