package com.example.armazem;

import com.example.exceptions.EstoqueQuantidadeException;
import com.example.exceptions.IngredienteNaoEncontradoException;
import com.example.exceptions.QuantidadeInvalidaException;
import com.example.ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {

    private TreeMap<Ingrediente, Integer> estoqueTreeMap = new TreeMap<>();

    public Armazem(TreeMap<Ingrediente, Integer> estoqueTreeMap) {
        this.estoqueTreeMap = estoqueTreeMap;
    }

    public TreeMap<Ingrediente, Integer> getEstoqueTreeMap() {
        return estoqueTreeMap;
    }

    public void setEstoqueTreeMap(TreeMap<Ingrediente, Integer> estoqueTreeMap) {
        this.estoqueTreeMap = estoqueTreeMap;
    }

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {

        if (!estoqueTreeMap.containsKey(ingrediente)) {
            estoqueTreeMap.put(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente já cadastrado");
        }
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {

        if (estoqueTreeMap.containsKey(ingrediente)) {
            estoqueTreeMap.remove(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

        if (estoqueTreeMap.containsKey(ingrediente)) {
            if (quantidade > 0) {
                int qtd = estoqueTreeMap.get(ingrediente) + quantidade;
                estoqueTreeMap.put(ingrediente, qtd);
            } else {
                throw new QuantidadeInvalidaException();
            }
        } else {
            throw new IngredienteNaoEncontradoException();
        }
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

        if (estoqueTreeMap.containsKey(ingrediente)) {
            if (quantidade > 0) {
                if (estoqueTreeMap.get(ingrediente) >= quantidade) {
                    int qtd = estoqueTreeMap.get(ingrediente) - quantidade;
                    estoqueTreeMap.put(ingrediente, qtd);
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
        if (estoqueTreeMap.containsKey(ingrediente)) {
            qtdEmEstoque = estoqueTreeMap.get(ingrediente);
        }
        return qtdEmEstoque;
    }

}
