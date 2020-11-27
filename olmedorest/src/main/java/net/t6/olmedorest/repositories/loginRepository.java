package net.t6.olmedorest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.t6.olmedorest.entities.login;


@Repository
public interface loginRepository extends JpaRepository<login, Long>{

	
	 public login existsByCorreo(String correo);//, String contrasenia);
	
	List<login> findByCorreoAndContrasenia(String correo, String contrasenia);
	
	
}
