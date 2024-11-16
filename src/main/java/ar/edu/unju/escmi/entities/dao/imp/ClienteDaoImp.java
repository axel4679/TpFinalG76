package ar.edu.unju.escmi.entities.dao.imp;

import javax.persistence.EntityManager;

import ar.edu.unju.escmi.conf.EmfSingleton;
import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.dao.ClienteDAO;

public class ClienteDaoImp implements ClienteDAO {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();


	@Override
	public void guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
		}catch(Exception e) {
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar el objeto autor");
		}finally {
			//manager.close();
		}

	}

	@Override
	public void buscarClienteID(long id) {
		// TODO Auto-generated method stub

	}



	@Override
	public void modificarCliente(Cliente clienteMod) {
		// TODO Auto-generated method stub
		try {
			manager.getTransaction().begin();
			manager.merge(clienteMod);
			manager.getTransaction().commit();
		}catch(Exception e) {
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo modificar el cliente");
		}finally {
			//manager.close();
		}

	}

}
