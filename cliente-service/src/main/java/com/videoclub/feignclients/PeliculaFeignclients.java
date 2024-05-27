package com.videoclub.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.videoclub.modelo.Juego;
import com.videoclub.modelo.Pelicula;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name="pelicula-service",url="http://localhost:8002")


public interface PeliculaFeignclients {
	
	@PostMapping("/pelicula")
	public Pelicula save(@RequestBody Pelicula pelicula);
	
	@GetMapping("/pelicula/cliente/{clienteId}")
	public List<Pelicula> getMovie(@PathVariable("clienteId") Long clienteId);

}
