
package net.t6.olmedorest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.Pago;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

	Optional <Pago> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
	
	List<Pago> findByEstadoContaining(String estado);
	
}
