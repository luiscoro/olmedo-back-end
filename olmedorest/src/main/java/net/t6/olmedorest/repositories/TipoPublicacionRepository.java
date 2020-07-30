
package net.t6.olmedorest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.TipoPublicacion;

import java.util.Optional;

@Repository
public interface TipoPublicacionRepository extends JpaRepository<TipoPublicacion, Long> {

	Optional <TipoPublicacion> findById(Long id);
	
	boolean existsByDetalle(String detalle);
	
	@Transactional
	void deleteById(Long id);
	
}
