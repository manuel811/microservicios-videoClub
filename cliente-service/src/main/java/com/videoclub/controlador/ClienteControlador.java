package com.videoclub.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juegoservice.dto.ClientePeliculas;
import com.videoclub.entidades.Cliente;
import com.videoclub.modelo.Juego;
import com.videoclub.modelo.Pelicula;
import com.videoclub.servicios.ClienteServicio;

@RestController
@RequestMapping("/cliente")
public class ClienteControlador {

	@Autowired
	private ClienteServicio clienteServicio;

	@PostMapping()
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) throws Exception {

		try {
			Cliente nuevoCliente = clienteServicio.save(cliente);
			// return ResponseEntity.status(HttpStatus.OK).body(nuevoCliente);
			return ResponseEntity.ok(nuevoCliente);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("")
	public ResponseEntity<List<Cliente>> findAll() throws Exception {

		try {
			List<Cliente> clientes = clienteServicio.findAll();
			return ResponseEntity.ok(clientes);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable("id") Long id) throws Exception {

		try {
			Cliente clienteById = clienteServicio.findById(id);
			if (clienteById == null) {
		
				return ResponseEntity.notFound().build();
			
			}
			return ResponseEntity.ok(clienteById);
			
		}
		
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}

	@PutMapping("/{id}")

	public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody Cliente cliente) throws Exception {

		try {
			Cliente clienteActualizado = clienteServicio.update(id, cliente);
			return ResponseEntity.ok(clienteActualizado);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Cliente> delete(@PathVariable("id") Long id) throws Exception {

		try {
			Cliente cliente = clienteServicio.findById(id);
			if (cliente==null) {
				clienteServicio.delete(id);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
	
	
	//RestTemplate
	
	@GetMapping("/pelicula/{clienteId}")
	public ResponseEntity<ClientePeliculas> listarPeliculas(@PathVariable("clienteId") Long id ) throws Exception{
		ClientePeliculas clientePeliculas = new ClientePeliculas();
		try {
			Cliente cliente=clienteServicio.findById(id);
			if(cliente==null) {
				return ResponseEntity.notFound().build();
				
			}
			List<Pelicula>peliculas=clienteServicio.getMovies(id);
			
			clientePeliculas.setCliente(cliente);
			clientePeliculas.setListaPeliculas(peliculas);
			if(peliculas.isEmpty()) {
				clientePeliculas.setMensaje("El cliente no tiene peliculas");
			}
			
			return ResponseEntity.ok(clientePeliculas);
		}catch(Exception E) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/juego/{clienteId}")
	public ResponseEntity<List<Juego>> listarJuegos(@PathVariable("clienteId") 	Long id ) throws Exception{
		try {
			Cliente cliente=clienteServicio.findById(id);
			if(cliente==null) {
				return ResponseEntity.notFound().build();
				
			}
			List<Juego>juegos=clienteServicio.getGames(id);
			return ResponseEntity.ok(juegos);
		}catch(Exception E) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//FeignClient
	
	@PostMapping("/pelicula/{clienteId}")
	public ResponseEntity<Pelicula>guardarPelicula(@PathVariable("clienteId") Long clienteId, @RequestBody Pelicula pelicula){
		Pelicula nuevaPelicuLa=clienteServicio.saveMovie(clienteId, pelicula);
		return ResponseEntity.ok(nuevaPelicuLa);
		
	}
	
	@PostMapping("/juego/{clienteId}")
	public ResponseEntity<Juego>guardarJuego(@PathVariable("clienteId") Long clienteId,@RequestBody Juego juego){
		Juego nuevoJuego=clienteServicio.saveJuego(clienteId, juego);
		return ResponseEntity.ok(nuevoJuego);
		
	}
	
	@GetMapping("/todos/{clienteId}")
	public ResponseEntity<Map<String, Object>> listarTodosProductos(@PathVariable("clienteId") Long clienteId){
		Map<String, Object>resultado=clienteServicio.getClientAndPelisAndJuegos(clienteId);
		return ResponseEntity.ok(resultado);
		
	}
	
	
	
	
	
	
	

}
