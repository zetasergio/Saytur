package co.edu.uan.app.siatur.view;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.model.entity.Rol;
import co.edu.uan.app.siatur.model.entity.Usuario;
import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.UsuarioService;
import co.edu.uan.app.siatur.util.FacesUtils;


@ManagedBean(name = UsuarioBean.BEAN_NAME, eager = true)
@CustomScoped(value = "#{window}")
public class UsuarioBean   implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "usuarioBean";
	public static final String PAGE_NAME = "gestionar_usuarios";
	private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);
	

	@EJB
	UsuarioService usuarioService;
	private Usuario usuario;
	private List<Usuario> listUsuario;
	private boolean visiblePopup;

	private String headerDialog;
	
	
	@PostConstruct
	public void init() {
		this.listUsuario = null;
		this.usuario = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}
	
	private void openPopup() {
		this.visiblePopup = true;
	}
	
	private void closedPopup() {
		this.visiblePopup = false;
	}
	
	
	public List<Usuario> getUsuarioAll() {
		this.listUsuario = usuarioService.getAll();
		return this.listUsuario;
	}
	
	
	

	public void addUsuario(ActionEvent event) {
		logger.info("Entro a addUsuario(event:" + event + ")");

		this.usuario = new Usuario();
		this.usuario.setEditable(Boolean.TRUE);
	    this.usuario.setVersion(1);
		this.usuario.setNombre("");
		this.usuario.setApellido("");
		this.usuario.setDireccion("");
		this.usuario.setIdentificacion("");
		this.usuario.setCorreo("");
		this.usuario.setEstado(Constantes.ESTADO_ACTIVO);

		this.headerDialog = "Nuevo Usuario";
		this.openPopup();

		logger.info("Saliendo de addUsuario(usuario:" + usuario + ")");

	}
	
	
	
	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				usuarioService.save(this.usuario);
				this.getUsuarioAll();
				this.closedPopup();
				
			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Usuario", "Error al guardar el Usuario", e.getMessage());
				logger.error("Error al guardar Usuario. "+e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
		return PAGE_NAME;
	}
	
	
	
	
	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.usuario == null) {
			detail = "No existe un objeto USUARIO inicializado";
			valid = false;
			
		} else if (StringUtils.isBlank(this.usuario.getNombre())) {
			detail = "Se debe ingresar el nombre del usuario";
			valid = false;
		}

		if (!valid) {
			FacesUtils.addMessageError("Guardar Usuario", "Error al guardar el Usuario", detail);
			logger.error("Error validando el usuario a guardar. "+detail);
		}

		logger.info("Saliendo de validateSaveAction()");
		return valid;
	}
	
	
	
	public void editUsuario(ActionEvent event) {
		logger.info("Entro a editUsuario(event:" + event + ")");
		
        UIComponent tmpComponent = event.getComponent();
        while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
            tmpComponent = tmpComponent.getParent();
        }
        if (tmpComponent != null && (tmpComponent instanceof UIData)) {
            Object tmpRowData = ((UIData) tmpComponent).getRowData();
            if (tmpRowData instanceof Usuario) {
            	this.usuario = (Usuario) tmpRowData;
            }
        }

		this.headerDialog = "Editar Usuario";
		this.openPopup();

		logger.info("Saliendo de editUsuario(Usuario:" + usuario + ")");

	}
	
	public String cancelAction() {
		logger.info("Entró a cancelAction()");

		this.closedPopup();

		logger.info("Saliendo de cancelAction()");
		return PAGE_NAME;
	}
	
	
	
	public String getHeaderDialog() {
		return this.headerDialog;
	}

	public void setHeaderDialog(String headerDialog) {
		this.headerDialog = headerDialog;
	}
	
	
	
	public Usuario getUsuario() {

		logger.info("this.usuario = " + this.usuario);
		if (this.usuario != null)
			logger.info("this.usuario.getNombre() = " + this.usuario.getNombre());

		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}
	
	
	public void setPkUsuario(long id){
		if(this.usuario != null){
			this.usuario.setId(id);
		}
	}
	
	public long getPkUsuario(){
		long nombre = 0;
		if(this.usuario != null){
			nombre = this.usuario.getId();
		}
		
		return nombre;
	}
	
	
	public void setNombreUsuario(String nombre){
		if(this.usuario != null){
			this.usuario.setNombre(nombre);
		}
	}
	
	public String getNombreUsuario(){
		String nombre = "";
		if(this.usuario != null){
			nombre = this.usuario.getNombre();
		}
		
		return nombre;
	}
	
	
	public void setApellidoUsuario(String apellido){
		if(this.usuario != null){
			this.usuario.setApellido(apellido);
		}
	}
	
	public String getApellidoUsuario(){
		String apellido = "";
		if(this.usuario != null){
			apellido = this.usuario.getApellido();
		}
		
		return apellido;
	}
	
	public void setIdentificacionUsuario(String identificacion){
		if(this.usuario != null){
			this.usuario.setIdentificacion(identificacion);
		}
	}
	
	public String getIdentificacionUsuario(){
		String identificacion = "";
		if(this.usuario != null){
			identificacion = this.usuario.getIdentificacion();
		}
		
		return identificacion;
	}
	
	
	public void setCorreoUsuario(String correo){
		if(this.usuario != null){
			this.usuario.setCorreo(correo);
		}
	}
	
	public String getCorreoUsuario(){
		String correo = "";
		if(this.usuario != null){
			correo = this.usuario.getCorreo();
		}
		
		return correo;
	}
	
	
	public void setDireccionUsuario(String direccion){
		if(this.usuario != null){
			this.usuario.setDireccion(direccion);
		}
	}
	
	public String getDireccionUsuario(){
		String direccion = "";
		if(this.usuario != null){
			direccion = this.usuario.getDireccion();
		}
		
		return direccion;
	}
	
	
	public void setEstadoUsuario(int estado){
		if(this.usuario != null){
			this.usuario.setEstado(estado);
		}
	}
	
	public int getEstadoUsuario(){
		int estado = 0;
		if(this.usuario != null){
			estado = this.usuario.getEstado();
		}
		
		return estado;
	}
	
	
	public void setUserUsuario(String usuario){
		if(this.usuario != null){
			this.usuario.setUsuario(usuario);
		}
	}
	
	public String getUserUsuario(){
		String estado = "";
		if(this.usuario != null){
			estado = this.usuario.getUsuario();
		}
		
		return estado;
	}
	
	
	
	public void setContrasenaUsuario(String clave){
		if(this.usuario != null){
			this.usuario.setClave(clave);
		}
	}
	
	public String getContrasenaUsuario(){
		String clave = "";
		if(this.usuario != null){
			clave = this.usuario.getClave();
		}
		
		return clave;
	}
	
	
	
	
	public void closeFAjax(AjaxBehaviorEvent event){
        this.visiblePopup=false;
    }


}

