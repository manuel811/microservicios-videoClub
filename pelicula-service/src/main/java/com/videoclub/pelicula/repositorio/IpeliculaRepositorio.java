package com.videoclub.pelicula.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videoclub.pelicula.entidades.Pelicula;

@Repository
public interface IpeliculaRepositorio extends JpaRepository<Pelicula, Long>{

	public List<Pelicula>findByClienteId(Long clienteId);
}
