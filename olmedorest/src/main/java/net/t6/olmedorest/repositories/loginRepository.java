package net.t6.olmedorest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import net.t6.olmedorest.entities.login;


@Repository
public interface loginRepository extends JpaRepository<login, String>{

	
	List<login> findByCorreoContaining(String correo);
	
}
