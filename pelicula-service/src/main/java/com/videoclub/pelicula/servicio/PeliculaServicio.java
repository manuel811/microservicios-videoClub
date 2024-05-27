package com.videoclub.pelicula.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videoclub.pelicula.entidades.Pelicula;
import com.videoclub.pelicula.repositorio.IpeliculaRepositorio;

@Service
public class PeliculaServicio implements IPeliculaServicio {

	@Autowired
	private IpeliculaRepositorio ipeliculaRepositorio;
	
	@Override
	public Pelicula save(Pelicula pelicula) throws Exception {
		try {
			Pelicula nuevaPelicula = ipeliculaRepositorio.save(pelicula);
			return nuevaPelicula;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}


	@Override
	public List<Pelicula> findAll() throws Exception {
		try {
			List<Pelicula> peliculas = ipeliculaRepositorio.findAll();
			return peliculas;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Pelicula findById(Long id) throws Exception {
		try {
			Optional<Pelicula> peliculaId = ipeliculaRepositorio.findById(id);
			if (peliculaId.isPresent()) {
				return peliculaId.get();
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}


	
	@Override
	public Pelicula update(Long id, Pelicula pelicula) throws Exception {
		try {
			Optional<Pelicula> peliculaPorId = ipeliculaRepositorio.findById(id);// Encontramos cliente por id
			Pelicula peliculaActualizada = peliculaPorId.get(); // Obtenemos el cliente
			peliculaActualizada = ipeliculaRepositorio.save(pelicula);// Guardamos el cliente nuevo

			return peliculaActualizada;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	

	@Override
	public void delete(Long id) throws Exception {
		try {
			ipeliculaRepositorio.deleteById(id);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	
	//RestTemplate
	
	public List<Pelicula> findByClienteId(Long clienteId) throws Exception {
		try {
			List<Pelicula> peliculas = ipeliculaRepositorio.findByClienteId(clienteId);
			
				return peliculas;
			
			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	

	
	
	



}
