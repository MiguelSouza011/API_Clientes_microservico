package com.miguelsouza.clientes.mapper;

import com.miguelsouza.clientes.dto.ClienteRequestDTO;
import com.miguelsouza.clientes.dto.ClienteResponseDTO;
import com.miguelsouza.clientes.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteRequestDTO dto);

    ClienteResponseDTO toDTO(Cliente cliente);

    @Mapping(target = "codigo", ignore = true)
    void atualizarEntidade(
            ClienteRequestDTO dto,
            @MappingTarget Cliente cliente
    );
}
