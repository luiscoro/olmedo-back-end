
package net.t6.olmedorest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional <Usuario> findById(Long id);
	
	boolean existsByNombreUsuario(String nombreUsuario);
	
	boolean existsByCorreo(String correo);
	
	//boolean existsByNombreCompleto(String nombreCompleto);
	
	@Transactional
	void deleteById(Long id);
	
	List<Usuario> findByNombreUsuarioContaining(String nombreUsuario);
	
	List<Usuario> findByCorreoContaining(String correo);
	
}
