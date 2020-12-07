
package net.t6.olmedorest.entities;
import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long id;
	
	@Column(name="nombre_usuario", unique=true)
	private String nombreUsuario;
	
	@Column(name="correo_usuario", unique=true)
	private String correo;
	
	@Column(name="contrasenia_usuario")
	private String contrasenia;
	
	@Column(name="sexo_usuario")
	private String sexo;
	
	@Basic
	@Column(name="fecha_nacimiento_usuario")
	private Date fechaNacimiento;
	
	@Column(name="telefono_usuario")
	private String telefono;
	
	@Column(name="direccion_usuario")
	private String direccion;
	
	@Column(name="puntaje_beneficio")
	private int puntajeBeneficio;
	
	@Basic
	@CreationTimestamp
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@Column(name="id_localidad", nullable=false)
	private int localidad;
	
	@Column(name="id_rol", nullable=false)
	private int idRol;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombreUsuario;
	}
	
	public void setNombre(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	 
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getPuntajeBeneficio() {
		return puntajeBeneficio;
	}
	
	public void setPuntajeBeneficio(int puntajeBeneficio) {
		this.puntajeBeneficio = puntajeBeneficio;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public int getIdLocalidad() {
		return localidad;
	}
	
	public void setIdLocalidad(int localidad) {
		this.localidad = localidad;
	}
	
	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}


}
