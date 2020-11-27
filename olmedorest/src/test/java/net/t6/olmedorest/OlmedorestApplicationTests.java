package net.t6.olmedorest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import net.t6.olmedorest.entities.Usuario;
import net.t6.olmedorest.entities.login;
import net.t6.olmedorest.repositories.UsuarioRepository;
import net.t6.olmedorest.repositories.loginRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FormApplicationTests {

	@Autowired
	loginRepository repou1;
	
	@Autowired
	UsuarioRepository repo;
	
	@Test //CP01
	@Rollback(false)
    public void testloginpc01() {
    	Usuario logs =  new Usuario("alex a", "M", "099999999", "vario a", 3, "1234", "al@al.com", 2);
    	Usuario saveUser = repo.save(logs);
    	
    	assertThat(saveUser.getId()).isGreaterThan(0);
    }
	
    @Test //CP02
    public void testLoginCorrect() {
    	boolean usuario =  repo.existsByCorreo("admin@admin.com");
    	
    	assert usuario;
    }
    
    @Test //CP03
    public void testLoginIncorrect() {
    	boolean usuario =  repo.existsByCorreo("admin122@admin.com");
    	
    	assert !usuario;
    }
}
