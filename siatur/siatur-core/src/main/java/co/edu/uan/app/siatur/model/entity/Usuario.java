package co.edu.uan.app.siatur.model.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usu_id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@Column(name = "usu_version")
	private int version;
	
	@Column(name = "usu_usuario", nullable = false)
	private String usuario;
	
	@Column(name = "usu_estado")
	private int estado;
	
	@Column(name = "usu_clave", nullable = false)
	private String clave;
	
	@Column(name = "usu_nombre", nullable = true)
	private String nombre;
	
	@Column(name = "usu_apellido", nullable = true)
	private String apellido;
	
	@Column(name = "usu_identificacion", nullable = true)
	private String identificacion;
	
	@Column(name = "usu_correo", nullable = true)
	private String correo;
	
	@Column(name = "usu_direccion", nullable = true)
	private String direccion;
	
	@Column(name = "usu_editable", nullable = false)
	private Boolean editable;
	
	
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

	public String getUsuario() {
		return clave;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", estado=" + estado + ", apellido=" + apellido + ", identificacion=" + identificacion + ", correo=" + correo + ", direccion=" + direccion + "]";
	}
	
}

