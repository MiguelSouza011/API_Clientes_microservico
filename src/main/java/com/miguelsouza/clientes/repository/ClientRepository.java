package com.miguelsouza.clientes.repository;

import com.miguelsouza.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente, Long> {
}
