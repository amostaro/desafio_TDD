package com.example.exceptions;

public class IngredienteNaoEncontradoException extends IllegalArgumentException {
    public String getMessage() {
        return "Ingrediente não encontrado";
    }
}
