package ar.edu.unju.escmi.test;



import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.dao.imp.ClienteDaoImp;

class ClienteTest {
	ClienteDaoImp cliDao = new ClienteDaoImp();

	@Test
	void test() {
		Cliente cliente = new Cliente("1234567", "Lionel", "Messi", "Estados Unidos", "+54 4243421", true);
		
		cliDao.guardarCliente(cliente);
		assertTrue(cliente != null);
		//assertNotNull("La lista de clientes no debe ser nula",cliente );
		
	}

}
