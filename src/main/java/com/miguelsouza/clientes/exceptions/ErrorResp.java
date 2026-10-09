package com.miguelsouza.clientes.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResp(int status, String mensage, List<ErrorCamp> erro) {

    public static ErrorResp response(String mensage) {
        return new ErrorResp(HttpStatus.BAD_REQUEST.value(), mensage, List.of());
    }

    public static ErrorResp conflict(String mensage) {
        return new ErrorResp(HttpStatus.CONFLICT.value(), mensage, List.of());
    }
}