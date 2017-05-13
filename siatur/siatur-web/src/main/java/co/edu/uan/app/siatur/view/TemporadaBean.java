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

import co.edu.uan.app.siatur.model.entity.Temporada;
import co.edu.uan.app.siatur.model.entity.Usuario;
import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.TemporadaService;
import co.edu.uan.app.siatur.util.FacesUtils;

@ManagedBean(name = TemporadaBean.BEAN_NAME, eager = true)
@CustomScoped(value = "#{window}")
public class TemporadaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "temporadaBean";
	public static final String PAGE_NAME = "gestionar_temporadas";
	private static final Logger logger = LoggerFactory.getLogger(TemporadaBean.class);
	

	@EJB
	TemporadaService temporadaService;
	private Temporada temporada;
	private List<Temporada> listTemporada;
	private boolean visiblePopup;

	private String headerDialog;
	
	
	@PostConstruct
	public void init() {
		this.listTemporada = null;
		this.temporada = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}
	
	private void openPopup() {
		this.visiblePopup = true;
	}
	
	private void closedPopup() {
		this.visiblePopup = false;
	}
	
	
	
	public List<Temporada> getTemporadaAll() {
		this.listTemporada = temporadaService.getAll();
		return this.listTemporada;
	}
	
	
	

	public void addTemporada(ActionEvent event) {
		logger.info("Entro a addUsuario(event:" + event + ")");

		this.temporada = new Temporada();
		this.temporada.setEditable(Boolean.TRUE);
		this.temporada.setFecha("");;
		this.temporada.setClasificacion("");
		

		this.headerDialog = "Nueva temporada";
		this.openPopup();

		logger.info("Saliendo de addTemporada(temporada:" + temporada + ")");

	}
	
	
	
	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				temporadaService.save(this.temporada);
				this.getTemporadaAll();

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

		if (this.temporada == null) {
			detail = "No existe un objeto Temporada inicializado";
			valid = false;
		} else if (StringUtils.isBlank(this.temporada.getClasificacion())) {
			detail = "Se debe ingresar la temporada";
			valid = false;
		}

		if (!valid) {
			FacesUtils.addMessageError("Guardar Usuario", "Error al guardar la temporada", detail);
			logger.error("Error validando la temporada a guardar. "+detail);
		}

		logger.info("Saliendo de validateSaveAction()");
		return valid;
	}
	
	
	
	public void editUsuario(ActionEvent event) {
		logger.info("Entro a editTemporada(event:" + event + ")");
		
        UIComponent tmpComponent = event.getComponent();
        while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
            tmpComponent = tmpComponent.getParent();
        }
        if (tmpComponent != null && (tmpComponent instanceof UIData)) {
            Object tmpRowData = ((UIData) tmpComponent).getRowData();
            if (tmpRowData instanceof Temporada) {
            	this.temporada = (Temporada) tmpRowData;
            }
        }

		this.headerDialog = "Editar Usuario";
		this.openPopup();

		logger.info("Saliendo de editUsuario(Temporada:" + temporada + ")");

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
	
	
	
	public Temporada getUsuario() {

		logger.info("this.temporada = " + this.temporada);
		if (this.temporada != null)
			logger.info("this.temporada.getClasificacion() = " + this.temporada.getClasificacion());

		return this.temporada;
	}

	public void setUsuario(Temporada temporada) {
		this.temporada = temporada;
	}

	
	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}
	
	public void setFechaTemporada(String fecha){
		if(this.temporada != null){
			this.temporada.setFecha(fecha);
		}
	}
	
	public String getFechaTemporada(){
		String Fecha = "";
		if(this.temporada != null){
			Fecha = this.temporada.getFecha();
		}
		
		return Fecha;
	}
	
	public void setClasificacionTemporada(String clasificacion){
		if(this.temporada != null){
			this.temporada.setClasificacion(clasificacion);;
		}
	}
	
	public String getClasificacionTemporada(){
		String clasificacion = "";
		if(this.temporada != null){
			clasificacion = this.temporada.getClasificacion();
		}
		
		return clasificacion;
	}
	
	
	
	
	public void closeFAjax(AjaxBehaviorEvent event){
        this.visiblePopup=false;
    }

}