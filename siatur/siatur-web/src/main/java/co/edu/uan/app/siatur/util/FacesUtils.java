package co.edu.uan.app.siatur.util;

import java.security.Principal;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacesUtils {

	private static final Logger logger = LoggerFactory.getLogger(FacesUtils.class);

	public static Principal getPrincipal() {

		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
	}

	public static void addMessageInfo(String clientId, String summary, String detail) {
		addMessage(0, clientId, summary, detail);
	}

	public static void addMessageWarn(String clientId, String summary, String detail) {
		addMessage(1, clientId, summary, detail);
	}

	public static void addMessageError(String clientId, String summary, String detail) {
		addMessage(2, clientId, summary, detail);
	}
	
	public static void addMessageFatal(String clientId, String summary, String detail) {
		addMessage(3, clientId, summary, detail);
	}

	private static void addMessage(int index, String clientId, String summary, String detail){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		removeExistingMessages(facesContext);

		FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(index), summary,
				detail);
		facesContext.addMessage(clientId, facesMessage);
	}
	
	private static void removeExistingMessages(FacesContext facesContext) {
		// remove existing messages
		Iterator<FacesMessage> i = facesContext.getMessages();
		while (i.hasNext()) {
			i.next();
			i.remove();
		}
	}

}
