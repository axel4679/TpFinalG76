package ar.edu.unju.escmi.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.entities.Cliente;

class ClienteTest {
	IClienteDao clienteDao = new ClienteDaoImp();

	@Test
	void test() {
		Cliente cliente = new Cliente("a", "a", "s", "s", "s", true);
		clienteDao.altaCliente(cliente);
		assertTrue(cliente != null);
	}

}
