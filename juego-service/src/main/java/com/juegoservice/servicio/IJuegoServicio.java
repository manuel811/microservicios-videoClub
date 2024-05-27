package com.juegoservice.servicio;

import java.util.List;
import java.util.Optional;

import com.juegoservice.entidades.Juego;



public interface IJuegoServicio {

	public Juego save(Juego juego) throws Exception; // Crear

	public List<Juego> findAll() throws Exception; // Listado

	public Optional<Juego> findById(Long id) throws Exception; // Listado por Id

	public Juego update(Long id, Juego pelicula) throws Exception; // Actualizar
	
	public void deleteById(Long id) throws Exception; // Borrar
}
