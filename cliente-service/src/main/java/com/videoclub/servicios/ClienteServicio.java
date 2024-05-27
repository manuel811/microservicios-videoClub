package com.videoclub.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.videoclub.entidades.Cliente;
import com.videoclub.feignclients.JuegoFeignClients;
import com.videoclub.feignclients.PeliculaFeignclients;
import com.videoclub.modelo.Juego;
import com.videoclub.modelo.Pelicula;
import com.videoclub.repositorio.IClienteRepositorio;

@Service
public class ClienteServicio implements IClienteServicio {

	@Autowired
	private JuegoFeignClients juegoFeignClients;
	
	@Autowired
	private PeliculaFeignclients peliculaFeignclients;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private IClienteRepositorio iClienteRepositorio;
	
	


	@Override
	public Cliente save(Cliente cliente) throws Exception {

		try {
			Cliente nuevoCliente = iClienteRepositorio.save(cliente);
			return nuevoCliente;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Cliente> findAll() throws Exception {

		try {
			List<Cliente> clientes = iClienteRepositorio.findAll();
			return clientes;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public Cliente findById(Long id) throws Exception {

		try {
			Optional<Cliente> clienteById = iClienteRepositorio.findById(id);
			if (clienteById.isPresent()) {
				return clienteById.get();
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public Cliente update(Long id, Cliente cliente) throws Exception {

		try {
			Optional<Cliente> clientePorId = iClienteRepositorio.findById(id);// Encontramos cliente por id
			Cliente clienteActualizado = clientePorId.get(); // Obtenemos el cliente
			clienteActualizado = iClienteRepositorio.save(cliente);// Guardamos el cliente nuevo

			return clienteActualizado;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public void delete(Long id) throws Exception {

		try {
			iClienteRepositorio.deleteById(id);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	
	
	
	//RestTemplate (Metodos)
	
	public List<Pelicula> getMovies(Long clienteId){
		List<Pelicula>peliculas=restTemplate.getForObject("http://localhost:8002/pelicula/cliente/" +clienteId, List.class);
		return peliculas;
	}
	
	
	public List<Juego> getGames(Long clienteId){
		List<Juego>juegos=restTemplate.getForObject("http://localhost:8003/juego/cliente/" +clienteId, List.class);
		return juegos;
	}
	
	
	//FeignClients
	
	public Pelicula saveMovie(Long clienteId,Pelicula pelicula) {
		pelicula.setClienteId(clienteId);
		Pelicula nuevaPelicula=peliculaFeignclients.save(pelicula);
		return nuevaPelicula;
		
	
	}
	
	
	public Juego saveJuego(Long clienteId,Juego juego) {
		juego.setClienteId(clienteId);
		Juego nuevoJuego=juegoFeignClients.save(juego);
		return nuevoJuego;
		
	}
	
	
	public Map<String,Object>getClientAndPelisAndJuegos(Long clienteId){
		Map<String, Object>resultado=new HashMap<>();
		Cliente cliente=iClienteRepositorio.findById(clienteId).orElse(null);// buscar cliente
		
		if(cliente==null) {
			resultado.put("Mensaje", "El cliente no existe");
			return resultado;
		}
		
		resultado.put("Cliente", cliente);
		List<Pelicula>peliculas=peliculaFeignclients.getMovie(clienteId);
		if(peliculas == null || peliculas.isEmpty()) {
			resultado.put("Peliculas", "El cliente no tiene peliculas");
			
		}else {
		resultado.put("Peliculas", peliculas);
		}
		
		List<Juego>juegos=juegoFeignClients.getGame(clienteId);
		if(juegos == null || juegos.isEmpty()) {
			resultado.put("Juegos", "El cliente no tiene juegos");
			
		}else {
			resultado.put("juegos", juegos);
			}
		return resultado;
	}

}
