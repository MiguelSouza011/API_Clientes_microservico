package com.miguelsouza.clientes.controller;

import com.miguelsouza.clientes.dto.ClienteRequestDTO;
import com.miguelsouza.clientes.dto.ClienteResponseDTO;
import com.miguelsouza.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(
            @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> obterPorCodigo(
            @PathVariable Long codigo) {
        return ResponseEntity.ok(service.obterPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        service.deletarPorCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long codigo,
            @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(codigo, dto));
    }
}
