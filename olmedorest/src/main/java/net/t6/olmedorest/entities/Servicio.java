
package net.t6.olmedorest.entities;
import javax.persistence.*;

import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.Type;
	
@Entity
@Table(name = "servicio")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_servicio")
	private Long id;
	
	@Column(name="nombre_servicio")
	private String nombre;
	
	@Column(name="detalle_servicio")
	private String detalle;
	
	@Column(name="foto_servicio")
	@Type(type = "uuid-char")
	private UUID foto;
	
	@Column(name="estado_servicio")
	private String estado;
	
	@Column(name="valor_servicio")
	private Long valor;
	
	@Column(name="descuento_servicio")
	private Long descuento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_servicio", nullable=false)
	private TipoServicio tipoServicio;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<UsuarioServicio> usuarioServicios;
	
	public Servicio() {}

	public Servicio(String nombre, String detalle, UUID foto, String estado, Long valor, Long descuento,
			TipoServicio tipoServicio) {
		super();
		this.nombre = nombre;
		this.detalle = detalle;
		this.foto = foto;
		this.estado = estado;
		this.valor = valor;
		this.descuento = descuento;
		this.tipoServicio = tipoServicio;
	}

	
	public Set<UsuarioServicio> getUsuarioServicios() {
		return usuarioServicios;
	}

	public void setUsuarioServicios(Set<UsuarioServicio> usuarioServicios) {
		this.usuarioServicios = usuarioServicios;
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
	
	public Long getValor() {
		return valor;
	}
	
	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	public Long getDescuento() {
		return descuento;
	}
	
	public void setDescuento(Long descuento) {
		this.descuento = descuento;
	}
	
}
