package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.Cliente;

public interface IClienteDao {

	public void altaCliente(Cliente cliente);

	public void modificarCliente(Cliente cliente);

	public Cliente consultarClienteId(Long idcliente);
}
