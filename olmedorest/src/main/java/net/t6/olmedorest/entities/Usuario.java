
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
	
	//@Column(name="habilitado")
	//private Byte habilitado;
	
	//@Column(name="nombre_completo")
	//private String nombreCompleto;
	
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
	private Long puntajeBeneficio;
	
	@Basic
	@CreationTimestamp
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@Column(name="id_localidad", nullable=false)
	private int localidad;
	
	@Column(name="id_rol", nullable=false)
	private int idRol;
	
	/*@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Notificacion> notificaciones;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<UsuarioServicio> usuarioServicios;
	
	public Usuario() {}

	public Usuario(String nombreUsuario, String correo, String contrasenia, Boolean habilitado, String nombreCompleto, String sexo,
			Date fechaNacimiento, String telefono, String direccion, Long puntajeBeneficio, Date fechaCreacion,
			Localidad localidad) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.habilitado=habilitado;
		this.nombreCompleto = nombreCompleto;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
		this.puntajeBeneficio = puntajeBeneficio;
		this.fechaCreacion = fechaCreacion;
		this.localidad = localidad;
	} */ /*

	public Set<UsuarioServicio> getUsuarioServicios() {
		return usuarioServicios;
	}

	public void setUsuarioServicios(Set<UsuarioServicio> usuarioServicios) {
		this.usuarioServicios = usuarioServicios;
	}

	public Set<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Set<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	} */

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
	/*
	public Byte isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Byte habilitado) {
		this.habilitado = habilitado;
	}
 
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	} */
	
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
	
	public Long getPuntajeBeneficio() {
		return puntajeBeneficio;
	}
	
	public void setPuntajeBeneficio(Long puntajeBeneficio) {
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
