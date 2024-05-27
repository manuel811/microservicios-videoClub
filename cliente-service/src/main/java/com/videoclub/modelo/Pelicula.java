package com.videoclub.modelo;



import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pelicula {
	
	private String nombre;

	private int anio;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	private String pais;

	private int duracion;

	private int cantidad;

	private double precio;
	
	private Long clienteId;

}
