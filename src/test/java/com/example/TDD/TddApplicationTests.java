package com.example.TDD;

import com.example.armazem.Armazem;
import com.example.ingredientes.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TddApplicationTests {

	TreeMap<Ingrediente, Integer> novoEstoqueTreeMap = new TreeMap<>();
	Armazem estoqueArmazem = new Armazem(novoEstoqueTreeMap);

	@Test
	void contextLoad() {
	}

	@Test
	public void testCadastrarIngredienteEmEstoque() throws IllegalArgumentException {
		Base novaBase = new Base(TipoBase.Iorgute);
		try {
			// teste ingrediente novo
			estoqueArmazem.cadastrarIngredienteEmEstoque(novaBase);
			assertEquals(1, estoqueArmazem.getEstoqueTreeMap().size());

			// teste ingrediente repetido ERRO
			estoqueArmazem.cadastrarIngredienteEmEstoque(novaBase);

		} catch (IllegalArgumentException e) {
			assertEquals("Ingrediente já cadastrado", e.getMessage());
		}
	}

	@Test
	public void testDescadastrarIngredienteEmEstoque() throws IllegalArgumentException {
		Base novaBase = new Base(TipoBase.Iorgute);
		try {
			// teste remove ingrediente
			estoqueArmazem.cadastrarIngredienteEmEstoque(novaBase);
			estoqueArmazem.descadastrarIngredienteEmEstoque(novaBase);
			assertEquals(0,estoqueArmazem.getEstoqueTreeMap().size());

			// teste remove ingrediente ERRO
			estoqueArmazem.descadastrarIngredienteEmEstoque(novaBase);

		} catch (IllegalArgumentException e) {
			assertEquals("Ingrediente não encontrado", e.getMessage());
		}
	}

	@Test
	public void testAdicionarQuantidadeDoIngredienteEmEstoque() throws IllegalArgumentException {
		Topping novoTopping = new Topping(TipoTopping.Chocolate);
		Integer quantidade = 3;
		Integer novaQtd = 7;
		try {
			estoqueArmazem.cadastrarIngredienteEmEstoque(novoTopping);
			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, quantidade);
			assertEquals(true, estoqueArmazem.getEstoqueTreeMap().containsValue(quantidade));

			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, novaQtd);
			assertEquals(true, estoqueArmazem.getEstoqueTreeMap().containsValue(quantidade + novaQtd));

		} catch (IllegalArgumentException e) {
			assertEquals("Ingrediente não encontrado ou quantidade inválida", e.getMessage());
		}
	}
//
//	@Test
//	public void testReduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) throws IllegalArgumentException {
//		try {
//
//		} catch (IllegalArgumentException e) {
//			throw new IllegalArgumentException("Ingrediente não encontrado ou quantidade inválida");
//		}
//	}
//
//	@Test
//	public void testConsultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) throws IllegalArgumentException {
//		try {
//
//		} catch (IllegalArgumentException e) {
//			throw new IllegalArgumentException("Ingrediente não encontrado");
//		}
//	}

}
