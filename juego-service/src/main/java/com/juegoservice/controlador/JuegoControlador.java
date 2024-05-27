package com.juegoservice.controlador;

import java.util.List;
import java.util.Optional;

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

import com.juegoservice.entidades.Juego;
import com.juegoservice.servicio.JuegoServicio;

@RestController
@RequestMapping("/juego")
public class JuegoControlador {

	@Autowired
	private JuegoServicio juegoServicio;

	@PostMapping()
	public ResponseEntity<Juego> save(@RequestBody Juego juego) throws Exception {

		try {
			Juego nuevoJuego = juegoServicio.save(juego);
			// return ResponseEntity.status(HttpStatus.OK).body(nuevoCliente);
			return ResponseEntity.ok(nuevoJuego);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("")
	public ResponseEntity<List<Juego>> findAll() throws Exception {

		try {
			List<Juego> juegos = juegoServicio.findAll();
			return ResponseEntity.ok(juegos);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Juego> findById(@PathVariable("id") Long id) throws Exception {

		try {
			Optional<Juego> juegoId = juegoServicio.findById(id);
			if (juegoId == null) {

				return ResponseEntity.ok(juegoId.get());
			} else {
				return ResponseEntity.ok(new Juego());
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/{id}")

	public ResponseEntity<Juego> update(@PathVariable("id") Long id, @RequestBody Juego juego) throws Exception {

		try {
			Juego juegoActualizado = juegoServicio.update(id, juego);
			return ResponseEntity.ok(juegoActualizado);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Juego> delete(@PathVariable("id") Long id) throws Exception {

		try {
			Optional<Juego> juego = juegoServicio.findById(id);
			if (juego != null) {
				juegoServicio.deleteById(id);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	// RestTemplate

	@GetMapping("/cliente/{clienteId}")

	public ResponseEntity<List<Juego>> findByClienteId(@PathVariable("clienteId") Long clienteId) throws Exception {
		List<Juego> juegos = juegoServicio.findByClienteId(clienteId);
		if (juegos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(juegos);
	}
}
