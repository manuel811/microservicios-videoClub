package com.videoclub.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.videoclub.modelo.Juego;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name="juego-service",url="http://localhost:8003")
public interface JuegoFeignClients {
	
	@PostMapping("/juego")
	public Juego save(@RequestBody Juego juego);

	
	@GetMapping("/juego/cliente/{clienteId}")
	public List<Juego> getGame(@PathVariable("clienteId") Long clienteId);
	
}
