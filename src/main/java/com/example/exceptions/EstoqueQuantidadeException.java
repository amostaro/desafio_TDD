package com.example.exceptions;

public class EstoqueQuantidadeException extends IllegalArgumentException {
    public String getMessage() {
        return "Estoque com quantidade baixa";
    }
}
