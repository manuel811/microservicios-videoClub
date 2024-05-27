package com.videoclub.modelo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Juego {

	private String nombre;

	@Enumerated(EnumType.STRING)
	private Consola consola;

	private int anio;

	private int cantidad;

	private int precio;

	private Long clienteId;
}
