package net.t6.olmedorest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.sql.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import antlr.collections.List;
import net.t6.olmedorest.controllers.UsuarioController;
import net.t6.olmedorest.entities.Usuario;
import net.t6.olmedorest.entities.login;
import net.t6.olmedorest.repositories.UsuarioRepository;
import net.t6.olmedorest.repositories.loginRepository;
import net.t6.olmedorest.services.UsuarioService;

@RunWith(SpringRunner.class) 
@SpringBootTest
class primerSprint {

	@InjectMocks
	private UsuarioService serviceU;
	
    @Mock
    private UsuarioRepository repoU;
    
	// ------------  longin test unit  ---------------
    @Test //CP01
    public void testLoginValid()throws Exception{
    	Usuario marco = new Usuario();
    	marco.setCorreo("pruebas@admin.com");
    	marco.setContrasenia("1234");
        when(repoU.findByCorreoAndContrasenia("pruebas@admin.com","1234")).thenReturn(marco);
        
        Usuario found = repoU.findByCorreoAndContrasenia("pruebas@admin.com","1234");
    	assertThat(found.getCorreo()).isEqualTo("pruebas@admin.com");
    	assertThat(found.getContrasenia()).isEqualTo("1234");
    } 
     
    @Test //CP02
    public void testLoginValidButPasswIncorrect() throws Exception{
    	Usuario marco = new Usuario();
    	marco.setCorreo("admin@admin.com");
    	marco.setContrasenia("1234");
        when(repoU.findByCorreoAndContrasenia("admin@admin.com","1234567")).thenReturn(null);
    	Usuario login = repoU.findByCorreoAndContrasenia("admin@admin.com","1234567");
    	
    	assertThat(login).isNull();
    }
    
    @Test //CP03
    public void testLoginValidButNotPassw() throws Exception{
    	Usuario login = repoU.findByCorreoAndContrasenia("admin@admin.com","");
    	
    	assertThat(login).isNotEqualTo("admin@admin.com");
    }
    
    @Test //CP04
    public void testLoginuserInvalid() throws Exception{
    	Usuario login = repoU.findByCorreoAndContrasenia("incorrecto@admin.com","1234567");
    	
    	assertThat(login).isNull();
    }
    
    @Test //CP05
    public void testLoginNoMailButPassw() throws Exception {
    	Usuario login = repoU.findByCorreoAndContrasenia("","1234");
    	
    	assertThat(login).isNull();
    }
    									
    // ------------------- creacion de Usuario -----------------
    @Test //CP02.1
    public void testRegistroUser() throws Exception{
    	// creacion y seteo de los objetos
    	 Usuario marco = new Usuario();
    	 marco.setCorreo("creacionPrueba@hotmail.com");
    	 marco.setContrasenia("12");
    	 marco.setSexo("masculino");
    	 marco.setIdRol(1);
    	 marco.setIdLocalidad(2);
    	 
    	 //llamada de la clase serviceU para creacion del usuario
    	 when(serviceU.createUsuario(marco)).thenReturn(marco);
    	 when(repoU.findByCorreo(marco.getCorreo())).thenReturn(marco);
    	 
    	 Usuario nuevo = new Usuario();
    	 nuevo.setCorreo("creacionPrueba@hotmail.com");
    	 nuevo.setContrasenia("12");
    	 nuevo.setSexo("masculino");
    	 nuevo.setIdRol(1);
    	 nuevo.setIdLocalidad(2);
    	 Usuario savedUser = serviceU.createUsuario(nuevo);
    	 
    	 //buscar el usuario creado con la clase repo
    	 Usuario foundU = repoU.findByCorreo(nuevo.getCorreo());
    	 
    	assertThat(foundU.getCorreo()).isEqualTo(nuevo.getCorreo());
    }
    @Test //CP02.2
    public void testNotRegistroUser() throws Exception{
    	 Usuario marco = new Usuario();
    	 marco.setCorreo("creacionPrueba@hotmail.com"); //la base  de datos no acepta no null
//    	 marco.setContrasenia("12");
    	 marco.setSexo("masculino");
//    	 marco.setIdRol(1);
    	 marco.setIdLocalidad(2);
    	 
    	 when(serviceU.createUsuario(marco)).thenReturn(null);
    	 when(repoU.findByCorreo(marco.getCorreo())).thenReturn(null);
    	 
    	 Usuario nuevo = new Usuario();
    	 nuevo.setCorreo("creacionPrueba@hotmail.com");
    	 nuevo.setSexo("masculino");
    	 nuevo.setIdLocalidad(2);
    	 Usuario savedUser = serviceU.createUsuario(nuevo);
    	 
    	 Usuario foundU = repoU.findByCorreo(nuevo.getCorreo());
    	 
    	assertThat(foundU).isNull();
    }
    @Test //CP03.1
    public void testUpdateUser() throws Exception{
    	 Usuario marco = new Usuario();
    	 marco.setCorreo("creacionPrueba@hotmail.com"); //la base  de datos no acepta no null
    	 marco.setContrasenia("12");
    	 marco.setSexo("masculino");
    	 marco.setDireccion("barrio los olivos");
    	 marco.setNombre("usuario prueba");
    	 marco.setPuntajeBeneficio(10);
    	 marco.setIdRol(1);
    	 marco.setIdLocalidad(2);
    	 
    	 when(repoU.findByCorreo(marco.getCorreo())).thenReturn(marco);
    	 marco.setCorreo("actualizacionPrueba@hotmail.com");
    	 marco.setContrasenia("actualizar");
    	 marco.setIdLocalidad(6);
    	 when(repoU.save(marco)).thenReturn(marco);
    	 
    	 Usuario update = repoU.findByCorreo("creacionPrueba@hotmail.com");
    	 update.setCorreo("actualizacionPrueba@hotmail.com");
    	 update.setContrasenia("actualizar");
    	 update.setIdLocalidad(6);
    	 Usuario updateUser = repoU.save(update);
    	 
    	assertThat(updateUser.getCorreo()).isEqualTo("actualizacionPrueba@hotmail.com");
    	assertThat(updateUser.getContrasenia()).isEqualTo("actualizar");
    	assertThat(updateUser.getIdLocalidad()).isEqualTo(6);
    }
    
    @Test //CP04.1
    public void testDeleteUser() throws Exception{
    	Usuario marco = new Usuario();
	   	 marco.setCorreo("creacionPrueba@hotmail.com"); //la base  de datos no acepta no null
	   	 marco.setContrasenia("12");
	   	 marco.setSexo("masculino");
	   	 marco.setDireccion("barrio los olivos");
	   	 marco.setNombre("usuario prueba");
	   	 marco.setPuntajeBeneficio(10);
	   	 marco.setIdRol(1);
	   	 marco.setIdLocalidad(2);
    	 
	   	 when(repoU.findByCorreo(marco.getCorreo())).thenReturn(marco);
    	 when(repoU.deleteByCorreo(marco.getCorreo())).thenReturn(true);
    	 
    	 Usuario delete = repoU.findByCorreo("creacionPrueba@hotmail.com");
    	 boolean deleteUser = repoU.deleteByCorreo(delete.getCorreo());
    	 
    	assertThat(deleteUser).isEqualTo(true);
    }
    @Test //CP04.2
    public void testCalcelDeleteUser() throws Exception{
    	Usuario marco = new Usuario();
	   	 marco.setCorreo("creacionPrueba@hotmail.com"); //la base  de datos no acepta no null
	   	 marco.setContrasenia("12");
	   	 marco.setSexo("masculino");
	   	 marco.setDireccion("barrio los olivos");
	   	 marco.setNombre("usuario prueba");
	   	 marco.setPuntajeBeneficio(10);
	   	 marco.setIdRol(1);
	   	 marco.setIdLocalidad(2);
    	 
	   	 when(repoU.findByCorreo(marco.getCorreo())).thenReturn(null);//segun lo que retorna el find...()
    	 when(repoU.deleteByCorreo("")).thenReturn(false);
    	
    	 Usuario delete = repoU.findByCorreo("eliminarFalloPrueba@hotmail.com");
    	 String vacio = "";
    	 if(delete != null) {
    		 vacio = delete.getCorreo();
    	 }
    	 boolean deleteUser = repoU.deleteByCorreo(vacio);
    	 
    	assertThat(deleteUser).isEqualTo(false);
    }
}
