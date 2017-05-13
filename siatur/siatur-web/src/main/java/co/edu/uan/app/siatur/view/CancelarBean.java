package co.edu.uan.app.siatur.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.model.entity.Cancelar;
import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.CancelarService;
import co.edu.uan.app.siatur.util.FacesUtils;

@ManagedBean(name = CancelarBean.BEAN_NAME, eager = true)
@SessionScoped
public class CancelarBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "cancelarBean";
	public static final String PAGE_NAME = "gestionar_cancelar";
	private static final Logger logger = LoggerFactory.getLogger(CancelarBean.class);

	@EJB
	CancelarService cancelarService;

	private Cancelar cancelar;
	private List<Cancelar> listCancelar;
	private boolean visiblePopup;

	private String headerDialog;

	@PostConstruct
	public void init() {
		this.listCancelar = null;
		this.cancelar = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}

	private void openPopup() {
		this.visiblePopup = true;
	}
	private void closedPopup() {
		this.visiblePopup = false;
	}

	public List<Cancelar> getCancelarAll() {

		this.listCancelar = cancelarService.getAll();
		return this.listCancelar;
	}

	public void addCancelar(ActionEvent event) {
		logger.info("Entro a addCancelar(event:" + event + ")");

		this.cancelar = new Cancelar();
		this.cancelar.setVersion(1);
		this.cancelar.setEditable(Boolean.TRUE);
		this.cancelar.setEstado(Constantes.ESTADO_ACTIVO);
		this.cancelar.setNombre("");

		this.headerDialog = "Nuevo Cancelar";
		this.openPopup();

		logger.info("Saliendo de addCancelar(cancelar:" + cancelar + ")");

	}

	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				cancelarService.save(this.cancelar);
				this.getCancelarAll();
				this.closedPopup();

			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Cancelar", "Error al guardar el Cancelar", e.getMessage());
				logger.error("Error al guardar cancelar. "+e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
		return PAGE_NAME;
	}

	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.cancelar == null) {
			detail = "No existe un objeto Cancelar inicializado";
			valid = false;
		} else if (StringUtils.isBlank(this.cancelar.getNombre())) {
			detail = "Se debe ingresar el nombre del cancelar";
			valid = false;
		}

		if (!valid) {
			FacesUtils.addMessageError("Guardar Cancelar", "Error al guardar el Cancelar", detail);
			logger.error("Error validando el cancelar a guardar. "+detail);
		}

		logger.info("Saliendo de validateSaveAction()");
		return valid;
	}

	public String getHeaderDialog() {
		return this.headerDialog;
	}

	public void setHeaderDialog(String headerDialog) {
		this.headerDialog = headerDialog;
	}

	public Cancelar getCancelar() {

		logger.info("this.cancelar = " + this.cancelar);
		if (this.cancelar != null)
			logger.info("this.cancelar.getNombre() = " + this.cancelar.getNombre());

		return this.cancelar;
	}

	public void setCancelar(Cancelar cancelar) {
		this.cancelar = cancelar;
	}

	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}

}
