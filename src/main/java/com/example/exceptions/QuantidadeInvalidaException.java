package com.example.exceptions;

public class QuantidadeInvalidaException extends IllegalArgumentException {
    public String getMessage() {
        return "Quantidade inválida";
    }
}
