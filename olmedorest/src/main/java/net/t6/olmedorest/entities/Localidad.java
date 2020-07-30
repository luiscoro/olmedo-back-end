
package net.t6.olmedorest.entities;
import java.util.Set;

import javax.persistence.*;
	
@Entity
@Table(name = "localidad")
public class Localidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_localidad")
	private Long id;
	
	@Column(name="pais")
	private String pais;
	
	@Column(name="ciudad")
	private String ciudad;
	
	@OneToMany(mappedBy="localidad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Usuario> usuarios;
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
}
