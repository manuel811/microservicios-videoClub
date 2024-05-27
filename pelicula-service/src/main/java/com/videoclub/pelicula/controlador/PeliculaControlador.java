package com.videoclub.pelicula.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videoclub.pelicula.entidades.Pelicula;
import com.videoclub.pelicula.servicio.PeliculaServicio;


@RestController
@RequestMapping("/pelicula")
public class PeliculaControlador {

	@Autowired
	private PeliculaServicio peliculaServicio;

	@PostMapping()
	public ResponseEntity<Pelicula> save(@RequestBody Pelicula pelicula) throws Exception {

		try {
			Pelicula nuevaPelicula = peliculaServicio.save(pelicula);
			// return ResponseEntity.status(HttpStatus.OK).body(nuevoCliente);
			return ResponseEntity.ok(nuevaPelicula);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("")
	public ResponseEntity<List<Pelicula>> findAll() throws Exception {

		try {
			List<Pelicula> peliculas = peliculaServicio.findAll();
			return ResponseEntity.ok(peliculas);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pelicula> findById(@PathVariable("id") Long id) throws Exception {

		try {
			Pelicula peliculaId = peliculaServicio.findById(id);
			if (peliculaId != null) {

				return ResponseEntity.ok(peliculaId);
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		

	}

	@PutMapping("/{id}")

	public ResponseEntity<Pelicula> update(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) throws Exception {

		try {
			Pelicula peliculaActualizada = peliculaServicio.update(id, pelicula);
			return ResponseEntity.ok(peliculaActualizada);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Pelicula> delete(@PathVariable("id") Long id) throws Exception {

		try {
			Pelicula pelicula = peliculaServicio.findById(id);
			if (pelicula != null) {
				peliculaServicio.delete(id);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
	
	
	//RestTemplate
	@GetMapping("/cliente/{clienteId}")
	
	
	public ResponseEntity<List<Pelicula>> findByClienteId(@PathVariable("clienteId") Long clienteId) throws Exception{
		List<Pelicula>peliculas=peliculaServicio.findByClienteId(clienteId);
		
		return ResponseEntity.ok(peliculas);
	}
	
	
	
}
