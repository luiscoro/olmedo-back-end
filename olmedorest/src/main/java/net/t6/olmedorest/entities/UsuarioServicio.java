package net.t6.olmedorest.entities;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "usuario_servicio")
public class UsuarioServicio{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario_servicio")
	private Long id;
	
	@Basic
	@CreationTimestamp
	@Column(name="fecha_usuario_servicio")
	private Date fecha;
	
	@Column(name="archivo_solicitud")
	@Type(type = "uuid-char")
	private UUID archivoSolicitud;
	
	@Column(name="nombre_promocion")
	private String nombrePromocion;
	
	@Column(name="detalle_promocion")
	private String detallePromocion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_servicio", nullable=false)
	private Servicio servicio;
	
	@OneToMany(mappedBy="usuarioServicio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Pago> pagos;
	
	public UsuarioServicio() {}
	
	public UsuarioServicio(Date fecha, UUID archivoSolicitud, String nombrePromocion, String detallePromocion,
			Usuario usuario, Servicio servicio) {
		super();
		this.fecha = fecha;
		this.archivoSolicitud = archivoSolicitud;
		this.nombrePromocion = nombrePromocion;
		this.detallePromocion = detallePromocion;
		this.usuario = usuario;
		this.servicio = servicio;
	}

	public Set<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(Set<Pago> pagos) {
		this.pagos = pagos;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public UUID getArchivoSolicitud() {
		return archivoSolicitud;
	}
	
	public void setArchivoSolicitud(UUID archivoSolicitud) {
		this.archivoSolicitud = archivoSolicitud;
	}
	
	public String getNombrePromocion() {
		return nombrePromocion;
	}
	
	public void setNombrePromocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}
	
	public String getDetallePromocion() {
		return detallePromocion;
	}
	
	public void setDetallePromocion(String detallePromocion) {
		this.detallePromocion = detallePromocion;
	}
	  
}
