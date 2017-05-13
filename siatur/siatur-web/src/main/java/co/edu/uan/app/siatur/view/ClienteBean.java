package co.edu.uan.app.siatur.view;

import java.io.Serializable;
import java.util.List;
import java.util.function.LongUnaryOperator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.model.entity.Cliente;

import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.ClienteService;

import co.edu.uan.app.siatur.util.FacesUtils;

@ManagedBean(name = ClienteBean.BEAN_NAME)

@CustomScoped(value = "#{window}")
public class ClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "clienteBean";
	public static final String PAGE_NAME = "gestionar_clientes";
	private static final Logger logger = LoggerFactory.getLogger(ClienteBean.class);

	@EJB
	ClienteService clienteService;

	private Cliente cliente;
	private List<Cliente> listCliente;
	private boolean visiblePopup;

	private String headerDialog;

	@PostConstruct
	public void init() {
		this.listCliente = null;
		this.cliente = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}

	private void openPopup() {
		this.visiblePopup = true;
	}

	private void closedPopup() {
		this.visiblePopup = false;
	}

	public List<Cliente> getClienteAll() {

		this.listCliente = clienteService.getAll();
		return this.listCliente;
	}

	public void addCliente(ActionEvent event) {
		logger.info("Entro a addCliente(event:" + event + ")");

		this.cliente = new Cliente();
		this.cliente.setVersion(1);

		this.cliente.setNombre("");
		this.cliente.setId(null);
		this.cliente.setTelefonoFijo("");
		this.cliente.setTelefonoCelular("");
		this.cliente.setDireccionCasa("");
		this.cliente.setEmail("");
		this.headerDialog = "Nuevo Cliente";
		this.openPopup();

		logger.info("Saliendo de addCliente(cliente:" + cliente + ")");

	}

	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				clienteService.save(this.cliente);
				this.getClienteAll();
				this.closedPopup();

			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Cliente", "Error al guardar el Cliente", e.getMessage());
				logger.error("Error al guardar cliente. " + e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
		return PAGE_NAME;
	}

	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.cliente == null) {

			detail = "No existe un objeto Cliente inicializado";
			valid = false;

		} else if (StringUtils.isBlank(this.cliente.getNombre())) {

			detail = "Se debe ingresar el nombre del cliente";
			valid = false;
		} else if (StringUtils.isBlank(this.cliente.getTelefonoFijo())) {

			detail = "Se debe ingresar el telefono fijo del cliente";
			valid = false;
		} else if (StringUtils.isBlank(this.cliente.getTelefonoCelular())) {

			detail = "Se debe ingresar el telefono celular del cliente";
			valid = false;
		} else if (StringUtils.isBlank(this.cliente.getDireccionCasa())) {

			detail = "Se debe ingresar la direccion de la casa del cliente";
			valid = false;
		} else if (StringUtils.isBlank(this.cliente.getEmail())) {

			detail = "Se debe ingresar el email del cliente";
			valid = false;
		} 
		
		/*
		 * else if (Long(this.cliente.getId())) {
		 * 
		 * 
		 * detail = "Se debe ingresar el nombre del cliente"; valid = false; }
		 */

		if (!valid) {

			FacesUtils.addMessageError("Guardar Cliente", "Error al guardar el Cliente", detail);
			logger.error("Error validando el cliente a guardar. " + detail);
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

	public Cliente getCliente() {

		logger.info("this.cliente = " + this.cliente);
		if (this.cliente != null)
			logger.info("this.cliente.getNombre() = " + this.cliente.getNombre(),
					"this.cliente.getId() = " + this.cliente.getId(), 
					"this.cliente.getTelefonoFijo() = " + this.cliente.getTelefonoFijo(),
					"this.cliente.getTelefonoCelular() = " + this.cliente.getTelefonoCelular(),
					"this.cliente.getTelefonoDireccionCasa() = " + this.cliente.getDireccionCasa(),
					"this.cliente.getTelefonoEmail() = " + this.cliente.getEmail());
		

		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}

	public void setNombreCliente(String nombre) {
		if (this.cliente != null) {
			this.cliente.setNombre(nombre);
		}
	}

	public String getNombreCliente() {
		String nombre = "";
		if (this.cliente != null) {
			nombre = this.cliente.getNombre();
		}

		return nombre;
	}

	public void setId(Long id) {
		if (this.cliente != null) {
			this.cliente.setId(id);
		}
	}

	public Long getId() {
		Long id=null;
		if (this.cliente != null) {
			id = this.cliente.getId();
		}
		return id;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		if (this.cliente != null) {
			this.cliente.setTelefonoFijo(telefonoFijo);
		}
	}

	public String getTelefonoFijo() {
		String telefonoFijo = "";
		if (this.cliente != null) {
			telefonoFijo = this.cliente.getTelefonoFijo();
		}

		return telefonoFijo;
	}
	
	public void setTelefonoCelular(String telefonoCelular) {
		if (this.cliente != null) {
			this.cliente.setTelefonoCelular(telefonoCelular);
		}
	}

	public String getTelefonoCelular() {
		String telefonoCelular = "";
		if (this.cliente != null) {
			telefonoCelular = this.cliente.getTelefonoCelular();
		}

		return telefonoCelular;
	}
	
	public void setDireccionCasa(String direccionCasa) {
		if (this.cliente != null) {
			this.cliente.setDireccionCasa(direccionCasa);
		}
	}

	public String getDireccionCasa() {
		String direccionCasa = "";
		if (this.cliente != null) {
			direccionCasa = this.cliente.getDireccionCasa();
		}

		return direccionCasa;
	}
	
	public void setEmail(String email) {
		if (this.cliente != null) {
			this.cliente.setEmail(email);
		}
	}

	public String getEmail() {
		String email = "";
		if (this.cliente != null) {
			email = this.cliente.getEmail();
		}

		return email;
	}
	
	/*
	 * if closing with a client side api, ensure a listener is used to update
	 * the visible value on the server
	 */
	public void closeFAjax(AjaxBehaviorEvent event) {
		this.visiblePopup = false;
	}

}
