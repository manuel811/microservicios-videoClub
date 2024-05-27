package com.videoclub.pelicula.servicio;

import java.util.List;

import com.videoclub.pelicula.entidades.Pelicula;



public interface IPeliculaServicio {

	public Pelicula save(Pelicula pelicula) throws Exception; //Crear
	
	public void delete(Long id) throws Exception; // Borrar
	
	public List<Pelicula> findAll() throws Exception; // Listado
	
	public Pelicula findById(Long id) throws Exception; //Listado por Id
	
	public Pelicula update(Long id, Pelicula pelicula) throws Exception; //Actualizar
	
}
