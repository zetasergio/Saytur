package co.edu.uan.app.siatur.model.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import co.edu.uan.app.siatur.model.entity.Cliente;


@Remote
@Stateless
public class ClienteService {

	@PersistenceContext(unitName = "siatur-unit")
	private EntityManager em;

	public List<Cliente> getAll() {
		CriteriaQuery<Cliente> criteria = this.em.getCriteriaBuilder().createQuery(Cliente.class);
		return this.em.createQuery(criteria.select(criteria.from(Cliente.class))).getResultList();

	}

	public Cliente save(Cliente cliente) throws IllegalArgumentException, Exception{

		Cliente newCliente = null;
		
		if(cliente == null){
			throw new IllegalArgumentException("No hay objeto Cliente para guardar");
		}			
//		}else if(rol.getId() == null){
//			this.em.persist(rol);
//		}else if(rol.getId() != null){
			newCliente = this.em.merge(cliente);
//		}
		
		return newCliente;
	}
}
