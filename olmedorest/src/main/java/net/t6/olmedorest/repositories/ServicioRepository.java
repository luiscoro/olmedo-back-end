
package net.t6.olmedorest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.Servicio;

import java.util.Optional;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

	Optional <Servicio> findById(Long id);
	
	boolean existsByNombre(String nombre);
	
	@Transactional
	void deleteById(Long id);
	
	
	List<Servicio> findByNombreContaining(String nombre);
	
	List<Servicio> findByEstadoContaining(String estado);
	
}
