package com.example.armazem;

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

        if (!estoqueTreeMap.containsKey(ingrediente)) {
            estoqueTreeMap.remove(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

}
