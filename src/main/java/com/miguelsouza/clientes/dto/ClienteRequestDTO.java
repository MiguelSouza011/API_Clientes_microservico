package com.miguelsouza.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150,message = "Nome deve ter no máximo 150 caracteres")
        String nome,
        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "CPF inválido")
        String cpf,
        @Size(max = 100)
        String logradouro,
        @Size(max = 10)
        String numero,
        @Size(max = 100)
        String bairro,
        @Email(message = "E-mail inválido")
        @Size(max = 150)
        String email,
        @Size(max = 20)
        String telefone
) {
}
