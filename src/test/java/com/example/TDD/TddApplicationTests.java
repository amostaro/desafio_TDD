package com.example.TDD;

import com.example.armazem.Armazem;
import com.example.exceptions.EstoqueQuantidadeException;
import com.example.exceptions.IngredienteNaoEncontradoException;
import com.example.exceptions.QuantidadeInvalidaException;
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

		Topping toppingErro = new Topping(TipoTopping.Aveia);
		Integer qtdErro = -2;

		try {
			estoqueArmazem.cadastrarIngredienteEmEstoque(novoTopping);
			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, quantidade);
			assertEquals(true, estoqueArmazem.getEstoqueTreeMap().containsValue(quantidade));

			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, novaQtd);
			assertEquals(true, estoqueArmazem.getEstoqueTreeMap().containsValue(quantidade + novaQtd));

			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(toppingErro, novaQtd);
			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, qtdErro);

		} catch (IngredienteNaoEncontradoException e) {
			assertEquals("Ingrediente não encontrado", e.getMessage());
		} catch (QuantidadeInvalidaException e) {
			assertEquals("Quantidade inválida", e.getMessage());
		}
	}

	@Test
	public void testReduzirQuantidadeDoIngredienteEmEstoque() throws IllegalArgumentException {
		Topping novoTopping = new Topping(TipoTopping.Chocolate);
		Integer quantidade = 10;
		Integer qtdReduzir = 7;

		Topping toppingErro = new Topping(TipoTopping.Aveia);
		Integer qtdErroNegativa = -4;
		Integer qtdErro = 50;

		try {
			estoqueArmazem.cadastrarIngredienteEmEstoque(novoTopping);
			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, quantidade);

			estoqueArmazem.reduzirQuantidadeDoIngredienteEmEstoque(novoTopping, qtdReduzir);
			assertEquals(true, estoqueArmazem.getEstoqueTreeMap().containsValue(quantidade - qtdReduzir));

			estoqueArmazem.reduzirQuantidadeDoIngredienteEmEstoque(toppingErro, qtdReduzir);
			estoqueArmazem.reduzirQuantidadeDoIngredienteEmEstoque(novoTopping, qtdErroNegativa);
			estoqueArmazem.reduzirQuantidadeDoIngredienteEmEstoque(novoTopping, qtdErro);

		} catch (IngredienteNaoEncontradoException e) {
			assertEquals("Ingrediente não encontrado", e.getMessage());
		} catch (QuantidadeInvalidaException e) {
			assertEquals("Quantidade inválida", e.getMessage());
		} catch (EstoqueQuantidadeException e) {
			assertEquals("Estoque com quantidade baixa", e.getMessage());
		}
	}

	@Test
	public void testConsultarQuantidadeDoIngredienteEmEstoque() throws IllegalArgumentException {
		Fruta novaFruta = new Fruta(TipoFruta.Morango);
		Integer quantidade = 10;

		Topping toppingErro = new Topping(TipoTopping.Aveia);

		try {
			estoqueArmazem.cadastrarIngredienteEmEstoque(novaFruta);
			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novaFruta, quantidade);
			estoqueArmazem.consultarQuantidadeDoIngredienteEmEstoque(novaFruta);
			assertEquals(quantidade, estoqueArmazem.getEstoqueTreeMap().get(novaFruta));

			estoqueArmazem.consultarQuantidadeDoIngredienteEmEstoque(toppingErro);

		} catch (IllegalArgumentException e) {
			assertEquals("Ingrediente não encontrado", e.getMessage());
		}
	}

}
