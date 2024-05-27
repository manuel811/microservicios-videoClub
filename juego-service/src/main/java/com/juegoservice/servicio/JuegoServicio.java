package com.juegoservice.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.juegoservice.entidades.Juego;
import com.juegoservice.entidades.repositorio.JuegoRepositorio;




@Service

public class JuegoServicio implements IJuegoServicio{

	@Autowired
	private JuegoRepositorio juegoRepositorio ;
	
	@Override
	public Juego save(Juego juego) throws Exception {
		try {
			Juego nuevoJuego = juegoRepositorio.save(juego);
			return nuevoJuego;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Juego> findAll() throws Exception {
		try {
			List<Juego> juegos = juegoRepositorio.findAll();
			return juegos;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Juego> findById(Long id) throws Exception {
		try {
			Optional<Juego> juegoId = juegoRepositorio.findById(id);
			if (juegoId.isPresent()) {
				return juegoId;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public Juego update(Long id, Juego pelicula) throws Exception {
		try {
			Optional<Juego> juegoPorId = juegoRepositorio.findById(id);// Encontramos cliente por id
			Juego juegoActualizado = juegoPorId.get(); // Obtenemos el cliente
			juegoActualizado = juegoRepositorio.save(pelicula);// Guardamos el cliente nuevo

			return juegoActualizado;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			juegoRepositorio.deleteById(id);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	
	//RestTemplate
	
	public List<Juego> findByClienteId(Long idCliente) throws Exception {
		try {
			List<Juego> juegos = juegoRepositorio.findByClienteId(idCliente);
			if (juegos.isEmpty()) {
				return null;
			} else {
				return juegos;
			
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
