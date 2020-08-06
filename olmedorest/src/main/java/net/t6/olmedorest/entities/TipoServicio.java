
package net.t6.olmedorest.entities;

import javax.persistence.*;
	
@Entity
@Table(name = "tipo_servicio")
public class TipoServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_servicio")
	private Long id;
	
	@Column(name="detalle_tipo_servicio")
	private String detalle;
	
	/*@OneToMany(mappedBy="tipoServicio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Servicio> servicios;
	
	
	public Set<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
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
