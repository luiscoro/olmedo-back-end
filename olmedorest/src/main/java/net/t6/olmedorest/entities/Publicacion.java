
package net.t6.olmedorest.entities;
import javax.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.Type;
import java.sql.Date;
import org.hibernate.annotations.CreationTimestamp;
	
@Entity
@Table(name = "publicacion")
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_publicacion")
	private Long id;
	
	@Column(name="nombre_publicacion")
	private String nombre;
	
	@Column(name="detalle_publicacion")
	private String detalle;
	
	@Column(name="autor_publicacion")
	private String autor;
	
	@Basic
	@CreationTimestamp
	@Column(name="fecha_publicacion")
	private Date fecha;
	
	@Type(type = "uuid-char")
	@Column(name="foto_publicacion")
	private UUID foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_publicacion", nullable=false)
	private TipoPublicacion tipoPublicacion;
		
	public Publicacion() {}

	public Publicacion(String nombre, String detalle, String autor, Date fecha, UUID foto,
			TipoPublicacion tipoPublicacion) {
		super();
		this.nombre = nombre;
		this.detalle = detalle;
		this.autor = autor;
		this.fecha = fecha;
		this.foto = foto;
		this.tipoPublicacion = tipoPublicacion;
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
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public UUID getFoto() {
		return foto;
	}
	
	public void setFoto(UUID foto) {
		this.foto = foto;
	}
	
}
