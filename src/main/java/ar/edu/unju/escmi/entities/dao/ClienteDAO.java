package ar.edu.unju.escmi.entities.dao;

import ar.edu.unju.escmi.entities.Cliente;

public interface ClienteDAO {
	public void guardarCliente(Cliente cliente);
	public void buscarClienteID(long id);
	public void modificarCliente(Cliente clienteMod);
	
}
