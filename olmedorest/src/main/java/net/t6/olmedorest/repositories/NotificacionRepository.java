
package net.t6.olmedorest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.Notificacion;

import java.util.Optional;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

	Optional <Notificacion> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
	
}
