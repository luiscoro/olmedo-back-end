
package net.t6.olmedorest.entities;
import javax.persistence.*;
	
@Entity
@Table(name = "notificacion")
public class Notificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_notificacion")
	private Long id;
	
	@Column(name="nombre_notificacion")
	private String nombre;
	
	@Column(name="detalle_notificacion")
	private String detalle;
	
	@Column(name="estado_notificacion")
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	
	public Notificacion() {}

	public Notificacion(String nombre, String detalle, String estado, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.detalle = detalle;
		this.estado = estado;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
