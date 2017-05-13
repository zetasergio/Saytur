package co.edu.uan.app.siatur.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cliente_id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "cliente_version")
	private int version;

	@Column(name = "cliente_nombre", nullable = false)
	private String nombre;
	
	@Column(name = "cliente_telefonoFijo", nullable = false)
	private String telefonoFijo;
	
	@Column(name = "cliente_telefonoCelular", nullable = false)
	private String telefonoCelular;
	
	@Column(name = "cliente_direccionCasa", nullable = false)
	private String direccionCasa;
	
	@Column(name = "cliente_email", nullable = false)
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	
	public String getDireccionCasa() {
		return direccionCasa;
	}

	public void setDireccionCasa(String direccionCasa) {
		this.direccionCasa = direccionCasa;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [version=" + version + ", nombre=" + nombre + ",telefonoFijo=" + telefonoFijo + ",telefonoCelular=" + telefonoCelular + ", direccionCasa=" + direccionCasa + ", email=" + email + " ]";
	}

}
