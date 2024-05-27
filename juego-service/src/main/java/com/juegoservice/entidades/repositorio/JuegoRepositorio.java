package com.juegoservice.entidades.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juegoservice.entidades.Juego;


@Repository
public interface JuegoRepositorio extends JpaRepository<Juego,Long> {
	
	public List<Juego>findByClienteId(Long clienteId);
}
