
package net.t6.olmedorest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.t6.olmedorest.entities.IntegranteClub;

import java.util.Optional;

@Repository
public interface IntegranteClubRepository extends JpaRepository<IntegranteClub, Long> {

	Optional <IntegranteClub> findById(Long id);
	
	boolean existsByNombre(String nombre);
	
	@Transactional
	void deleteById(Long id);
	
	List<IntegranteClub> findByPerfilContaining(String perfil);
	
}
