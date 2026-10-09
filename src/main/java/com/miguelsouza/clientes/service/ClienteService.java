package com.miguelsouza.clientes.service;

import com.miguelsouza.clientes.dto.ClienteRequestDTO;
import com.miguelsouza.clientes.dto.ClienteResponseDTO;
import com.miguelsouza.clientes.exceptions.CpfDuplicadoException;
import com.miguelsouza.clientes.exceptions.ResourceNotFoundException;
import com.miguelsouza.clientes.mapper.ClienteMapper;
import com.miguelsouza.clientes.model.Cliente;
import com.miguelsouza.clientes.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientRepository repository;
    private final ClienteMapper mapper;

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        String cpf = dto.cpf().replaceAll("\\D", "");

        if (repository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Cliente cliente = mapper.toEntity(dto);
        cliente.setCpf(cpf);
        cliente = repository.save(cliente);
        return mapper.toDTO(cliente);
    }

    public ClienteResponseDTO obterPorCodigo(Long codigo){

        Cliente cliente = repository.findById(codigo)
                .orElseThrow( () -> new ResourceNotFoundException(codigo));
        return mapper.toDTO(cliente);
    }

    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void deletarPorCodigo(Long codigo) {
        if (!repository.existsById(codigo)) {
            throw new ResourceNotFoundException(codigo);
        }

        repository.deleteById(codigo);
    }

    public ClienteResponseDTO atualizar(
            Long codigo, ClienteRequestDTO dto) {

        Cliente cliente = repository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(codigo));

        String cpf = dto.cpf().replaceAll("\\D", "");

        if (!cliente.getCpf().equals(cpf)
                && repository.existsByCpf(cpf)) {
            throw new CpfDuplicadoException(
                    "CPF já cadastrado"
            );
        }

        mapper.atualizarEntidade(dto, cliente);
        cliente.setCpf(cpf);

        return mapper.toDTO(repository.save(cliente));
    }
}
