package ar.edu.unju.escmi.entities.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.conf.EmfSingleton;
import ar.edu.unju.escmi.entities.Salon;
import ar.edu.unju.escmi.entities.dao.SalonDAO;

public class SalonDAOImp implements SalonDAO {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void guardarSalon(Salon salon) {
		// TODO Auto-generated method stub
		try {
			manager.getTransaction().begin();
			manager.persist(salon);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar el objeto Salon");
		} finally {
			// manager.close();
		}

	}
	
	@Override
	public List<Salon> consultarSalon() {
		// TODO Auto-generated method stub
		TypedQuery<Salon> query = manager.createQuery("Select l from Salon l", Salon.class);
		List<Salon> Salones = query.getResultList();
		return Salones;

	}



}
