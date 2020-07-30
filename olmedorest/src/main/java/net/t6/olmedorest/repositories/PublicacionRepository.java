
package net.t6.olmedorest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.Publicacion;

import java.util.Optional;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

	Optional <Publicacion> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
	
	List<Publicacion> findByNombreContaining(String nombre);
	
}
