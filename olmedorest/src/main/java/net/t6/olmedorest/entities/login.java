package net.t6.olmedorest.entities;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuario")
public class login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long id;
	
	@Column(name="contrasenia_usuario")
	private String contrasenia;
	
	@Column(name="correo_usuario")
	private String correo;
	
	@Column(name="puntaje_beneficio")
	private int point;
	
	@Column(name="id_rol")
	private int idRol;
	
	public login(String contrasenia, String correo, int point, int idRol) {
		this.contrasenia = contrasenia;
		this.correo = correo;
		this.point = point;
		this.idRol = idRol;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setidRol(int idRol) {
		this.idRol = idRol;
	}
	
	public int getidRol() {
		return idRol;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public int getPuntajeBeneficio() {
		return point;
	}
	
	public void setPuntajeBeneficio(int point) {
		this.point = point;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

}
