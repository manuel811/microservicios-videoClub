package com.videoclub.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videoclub.entidades.Cliente;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Long> {

}
