package co.edu.uan.app.siatur.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.model.entity.Rol;
import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.RolService;
import co.edu.uan.app.siatur.util.FacesUtils;

@ManagedBean(name = RolBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class RolBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "rolBean";
	public static final String PAGE_NAME = "gestionar_roles";
	private static final Logger logger = LoggerFactory.getLogger(RolBean.class);

	@EJB
	RolService rolService;

	private Rol rol;
	private List<Rol> listRol;
	private boolean visiblePopup;

	private String headerDialog;

	@PostConstruct
	public void init() {
		this.listRol = null;
		this.rol = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}

	private void openPopup() {
		this.visiblePopup = true;
	}
	private void closedPopup() {
		this.visiblePopup = false;
	}

	public List<Rol> getRolAll() {

		this.listRol = rolService.getAll();
		return this.listRol;
	}

	public void addRol(ActionEvent event) {
		logger.info("Entro a addRol(event:" + event + ")");

		this.rol = new Rol();
		this.rol.setVersion(1);
		this.rol.setEditable(Boolean.TRUE);
		this.rol.setEstado(Constantes.ESTADO_ACTIVO);
		this.rol.setNombre("");

		this.headerDialog = "Nuevo Rol";
		this.openPopup();

		logger.info("Saliendo de addRol(rol:" + rol + ")");

	}

	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				rolService.save(this.rol);
				this.getRolAll();
				this.closedPopup();

			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Rol", "Error al guardar el Rol", e.getMessage());
				logger.error("Error al guardar rol. "+e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
		return PAGE_NAME;
	}

	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.rol == null) {
			
			detail = "No existe un objeto ROL inicializado";
			valid = false;
			
		} else if (StringUtils.isBlank(this.rol.getNombre())) {
			
			detail = "Se debe ingresar el nombre del rol";
			valid = false;
		}

		if (!valid) {
			
			FacesUtils.addMessageError("Guardar Rol", "Error al guardar el Rol", detail);
			logger.error("Error validando el rol a guardar. "+detail);
		}

		logger.info("Saliendo de validateSaveAction()");
		return valid;
	}
	
	public void editRol(ActionEvent event) {
		logger.info("Entro a editRol(event:" + event + ")");
		
        UIComponent tmpComponent = event.getComponent();
        while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
            tmpComponent = tmpComponent.getParent();
        }
        if (tmpComponent != null && (tmpComponent instanceof UIData)) {
            Object tmpRowData = ((UIData) tmpComponent).getRowData();
            if (tmpRowData instanceof Rol) {
            	this.rol = (Rol) tmpRowData;
            }
        }

		this.headerDialog = "Editar Rol";
		this.openPopup();

		logger.info("Saliendo de editRol(rol:" + rol + ")");

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

	public Rol getRol() {

		logger.info("this.rol = " + this.rol);
		if (this.rol != null)
			logger.info("this.rol.getNombre() = " + this.rol.getNombre());

		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}
	
	public void setNombreRol(String nombre){
		if(this.rol != null){
			this.rol.setNombre(nombre);
		}
	}
	
	public String getNombreRol(){
		String nombre = "";
		if(this.rol != null){
			nombre = this.rol.getNombre();
		}
		
		return nombre;
	}
	
	/*
     *  if closing with a client side api, ensure a listener is used to
     *  update the visible value on the server
     */
    public void closeFAjax(AjaxBehaviorEvent event){
        this.visiblePopup=false;
    }

}
