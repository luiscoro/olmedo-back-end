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
	
	@Column(name="id_usuario")
	private Long idU;
	
	@Column(name="id_servicio")
	private Long idS;
	
	@Column(name="estado_servicio")
	private String estadoS;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdU() {
		return idU;
	}
	
	public void setIdU(Long idU) {
		this.idU = idU;
	}
	
	public Long getIdS() {
		return idS;
	}
	
	public void setIdS(Long idS) {
		this.idS = idS;
	}
	
	public String getEstadoS() {
		return estadoS;
	}
	
	public void setEstadoS(String estadoS) {
		this.estadoS = estadoS;
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
