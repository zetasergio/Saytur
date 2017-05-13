package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Temporada;


@Remote
@Stateless
public class TemporadaService {
	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	
	public List<Temporada> getAll() {
		CriteriaQuery<Temporada> criteria = this.em.getCriteriaBuilder().createQuery(Temporada.class);
		return this.em.createQuery(criteria.select(criteria.from(Temporada.class))).getResultList();
	}
	

	public Temporada save(Temporada usr) throws IllegalArgumentException, Exception{

		Temporada newTemporada = null;
		
		if(usr == null){
			throw new IllegalArgumentException("No hay objeto usuario para guardar");
		}			
//		}else if(rol.getId() == null){
//			this.em.persist(rol);
//		}else if(rol.getId() != null){
		newTemporada = this.em.merge(usr);
//		}
		
		return newTemporada;
	}
}
