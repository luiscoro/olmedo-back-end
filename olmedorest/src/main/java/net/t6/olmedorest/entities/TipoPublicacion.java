package net.t6.olmedorest.entities;


import javax.persistence.*;
	
@Entity
@Table(name = "tipo_publicacion")
public class TipoPublicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_publicacion")
	private Long id;
	
	@Column(name="detalle_tipo_publicacion")
	private String detalle;
	
	/*@OneToMany(mappedBy="tipoPublicacion", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Publicacion> publicaciones;
	

	public Set<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(Set<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}*/

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
}
