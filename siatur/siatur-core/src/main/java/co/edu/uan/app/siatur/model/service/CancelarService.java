package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Cancelar;

@Remote
@Stateless
public class CancelarService {

	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	public List<Cancelar> getAll() {
		CriteriaQuery<Cancelar> criteria = this.em.getCriteriaBuilder().createQuery(Cancelar.class);
		return this.em.createQuery(criteria.select(criteria.from(Cancelar.class))).getResultList();

	}
	
	
	public Cancelar save(Cancelar cancelar) throws IllegalArgumentException, Exception{

		Cancelar newCancelar = null;
		
		if(cancelar == null){
			throw new IllegalArgumentException("No hay objeto Cancelar para guardar");
		}			
//		}else if(cancelar.getId() == null){
//			this.em.persist(cancelar);
//		}else if(cancelar.getId() != null){
			newCancelar = this.em.merge(cancelar);
//		}
		
		return newCancelar;
	}
}
