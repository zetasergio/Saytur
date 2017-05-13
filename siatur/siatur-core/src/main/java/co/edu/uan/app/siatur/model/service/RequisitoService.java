package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Requisito;


@Remote
@Stateless
public class RequisitoService {

	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager e;

	public List<Requisito> getAll() {
		CriteriaQuery<Requisito> criteria = this.e.getCriteriaBuilder().createQuery(Requisito.class);
		return this.e.createQuery(criteria.select(criteria.from(Requisito.class))).getResultList();

	}
/*
	public Req save(Req req) throws IllegalArgumentException, Exception{

		Req newReq = null;
		
		if(req == null){
			throw new IllegalArgumentException("No hay objeto Requisito para guardar");
		}			
//		}else if(req.getId() == null){
//			this.em.persist(req);
//		}else if(req.getId() != null){
			newRol = this.em.merge(req);
//		}
		
		return newReq;
	}
	*/
}
