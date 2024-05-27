package com.juegoservice.dto;

import java.util.List;

import com.videoclub.entidades.Cliente;
import com.videoclub.modelo.Pelicula;

import lombok.Data;

@Data
public class ClientePeliculas {
	
	private Cliente cliente;
	private List<Pelicula> listaPeliculas;
	private String mensaje;
	

}
