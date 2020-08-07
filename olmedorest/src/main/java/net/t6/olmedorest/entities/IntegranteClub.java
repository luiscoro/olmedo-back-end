
package net.t6.olmedorest.entities;
import javax.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.Type;
import java.sql.Date;
	
@Entity
@Table(name = "integrante_club")
public class IntegranteClub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_integrante")
	private Long id;
	
	@Column(name="nombre_integrante")
	private String nombre;
	
	@Column(name="perfil_integrante")
	private String perfil;
	
	@Basic
	@Column(name="fecha_nacimiento_integrante")
	private Date fechaNacimiento;
	
	@Column(name="equipo_procedencia")
	private String equipoProcedencia;
	
	@Column(name="pais_procedencia")
	private String paisProcedencia;
	
	@Column(name="estatura_integrante")
	private float estatura;
	
	@Column(name="peso_integrante")
	private float peso;
	
	@Column(name="funcion_integrante")
	private String funcion;
	
	@Column(name="foto_integrante")
	@Type(type = "uuid-char")
	private UUID foto;

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
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getEquipoProcedencia() {
		return equipoProcedencia;
	}
	
	public void setEquipoProcedencia(String equipoProcedencia) {
		this.equipoProcedencia = equipoProcedencia;
	}
	
	public String getPaisProcedencia() {
		return paisProcedencia;
	}
	
	public void setPaisProcedencia(String paisProcedencia) {
		this.paisProcedencia = paisProcedencia;
	}
	
	
	
	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getFuncion() {
		return funcion;
	}
	
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	
	public UUID getFoto() {
		return foto;
	}
	
	public void setFoto(UUID foto) {
		this.foto = foto;
	}
	
}
