
package net.t6.olmedorest.repositories;

import net.t6.olmedorest.entities.UsuarioServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioServicioRepository extends JpaRepository<UsuarioServicio, Long> {

	Optional <UsuarioServicio> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
	
}
