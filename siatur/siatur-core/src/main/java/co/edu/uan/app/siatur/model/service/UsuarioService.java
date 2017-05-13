package co.edu.uan.app.siatur.model.service;


import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Usuario;

@Remote
@Stateless
public class UsuarioService {
	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	
	public List<Usuario> getAll() {
		CriteriaQuery<Usuario> criteria = this.em.getCriteriaBuilder().createQuery(Usuario.class);
		return this.em.createQuery(criteria.select(criteria.from(Usuario.class))).getResultList();
	}
	

	public Usuario save(Usuario usr) throws IllegalArgumentException, Exception{

		Usuario newUsuario = null;
		
		if(usr == null){
			throw new IllegalArgumentException("No hay objeto usuario para guardar");
		}	
		
		newUsuario = this.em.merge(usr);

		
		return newUsuario;
	}
}
	
	


