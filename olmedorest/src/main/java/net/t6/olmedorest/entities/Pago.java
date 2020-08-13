
package net.t6.olmedorest.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
	
@Entity
@Table(name = "pago")
public class Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pago")
	private Long id;
	
	@Basic
	@CreationTimestamp
	@Column(name="fecha_pago")
	private Date fecha;
	
	@Basic
	@CreationTimestamp
	@Column(name="hora_pago")
	private Time hora;
	
	@Type(type = "uuid-char")
	@Column(name="foto_pago")
	private UUID foto;
	
	@Column(name="estado_pago")
	private String estado;
	
	@Column(name="id_usuario_servicio")
	private Long idUS;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdUS() {
		return idUS;
	}
	
	public void setIdUS(Long idUS) {
		this.idUS = idUS;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Time getHora() {
		return hora;
	}
	
	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	public UUID getFoto() {
		return foto;
	}

	public void setFoto(UUID foto) {
		this.foto = foto;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
