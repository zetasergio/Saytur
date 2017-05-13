package co.edu.uan.app.siatur.view;

import java.io.Serializable;
import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.util.FacesUtils;


@ManagedBean(name=SessionBean.BEAN_NAME,eager=true)
@SessionScoped
public class SessionBean implements Serializable {
	public static final String BEAN_NAME = "sessionBean";
    private static final Logger logger = LoggerFactory.getLogger(SessionBean.class);
	
	public static final String HOME_PAGE = "home.xhtml";
	
	private Principal principal;	

	@PostConstruct
	public void init() {
	}
		
	public String getNombreCompletoUsuario(){
        
		this.principal = FacesUtils.getPrincipal();
       
		return this.principal !=null ? this.principal.getName(): "NO USUARIO";
	}
	
    public String logout() {
    	
//        logger.log(Level.INFO, "Entro a  logout()");
        
        try {
//        	logger.log(Level.INFO, "FacesContext.getCurrentInstance() = " + FacesContext.getCurrentInstance());
            ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();
//            logger.log(Level.INFO, "fc = " + fc);

            if (fc != null) {

                HttpSession session = (HttpSession) fc.getSession(true);
//                logger.log(Level.INFO, "session = " + session);

                if (session != null) {

                    session.invalidate();
//                    logger.log(Level.INFO, "Invalidada la sesion correctamente ");
                }
            }
        } catch (Exception ex) {
//        	logger.log(Level.SEVERE, "Error logout  ", ex);
        }
//        logger.log(Level.INFO, "Saliendo de  logout()");
        
        return "inicio";
    }
	
}
