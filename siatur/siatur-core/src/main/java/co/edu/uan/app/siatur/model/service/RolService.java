package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Rol;

@Remote
@Stateless
public class RolService {

	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	public List<Rol> getAll() {
		CriteriaQuery<Rol> criteria = this.em.getCriteriaBuilder().createQuery(Rol.class);
		return this.em.createQuery(criteria.select(criteria.from(Rol.class))).getResultList();

	}
	
	
	public Rol save(Rol rol) throws IllegalArgumentException, Exception{

		Rol newRol = null;
		
		if(rol == null){
			throw new IllegalArgumentException("No hay objeto Rol para guardar");
		}			

			newRol = this.em.merge(rol);
		
		return newRol;
	}
}
