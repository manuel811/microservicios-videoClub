package com.videoclub.servicios;

import java.util.List;

import com.videoclub.entidades.Cliente;

public interface IClienteServicio {

	public Cliente save(Cliente cliente) throws Exception; //Crear
	
	public List<Cliente> findAll() throws Exception; // Listado
	
	public Cliente findById(Long id) throws Exception; //Listado por Id
	
	public Cliente update(Long id, Cliente cliente) throws Exception; //Actualizar
	
	public void delete(Long id) throws Exception; // Borrar
	
	

	
}
