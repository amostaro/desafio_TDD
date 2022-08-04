package com.example.armazem;

import com.example.exceptions.EstoqueQuantidadeException;
import com.example.exceptions.IngredienteNaoEncontradoException;
import com.example.exceptions.QuantidadeInvalidaException;
import com.example.ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {

    private final TreeMap<Ingrediente, Integer> estoqueTreeMap;

    public Armazem(TreeMap<Ingrediente, Integer> estoqueTreeMap) {
        this.estoqueTreeMap = estoqueTreeMap;
    }

    public TreeMap<Ingrediente, Integer> getEstoqueTreeMap() {
        return estoqueTreeMap;
    }

//    public void setEstoqueTreeMap(TreeMap<Ingrediente, Integer> estoqueTreeMap) {
//        this.estoqueTreeMap = estoqueTreeMap;
//    }

    private boolean isContainsKey(Ingrediente ingrediente) {
        return estoqueTreeMap.containsKey(ingrediente);
    }

    private Integer getQtdEmEstoque(Ingrediente ingrediente) {
        return estoqueTreeMap.get(ingrediente);
    }

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {

        if (!isContainsKey(ingrediente)) {
            estoqueTreeMap.put(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente já cadastrado");
        }
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {

        if (isContainsKey(ingrediente)) {
            estoqueTreeMap.remove(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

        if (isContainsKey(ingrediente)) {
            if (quantidade > 0) {
                int qtdEmEstoque = getQtdEmEstoque(ingrediente) + quantidade;
                estoqueTreeMap.put(ingrediente, qtdEmEstoque);
            } else {
                throw new QuantidadeInvalidaException();
            }
        } else {
            throw new IngredienteNaoEncontradoException();
        }
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

        //TODO - verificar as exceções antes de entrar no mérito da verificação
        //TODO - separar método verificarQuantidadeInvalida()
        if (isContainsKey(ingrediente)) {
            if (quantidade > 0) {
                if (getQtdEmEstoque(ingrediente) >= quantidade) {
                    int qtdEmEstoque = getQtdEmEstoque(ingrediente) - quantidade;
                    estoqueTreeMap.put(ingrediente, qtdEmEstoque);
                } else {
                    throw new EstoqueQuantidadeException();
                }
            } else {
                throw new QuantidadeInvalidaException();
            }
        } else {
            throw new IngredienteNaoEncontradoException();
        }
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {

        Integer qtdEmEstoque = 0;
        if (isContainsKey(ingrediente)) {
            qtdEmEstoque = getQtdEmEstoque(ingrediente);
        }
        return qtdEmEstoque;
    }

}
