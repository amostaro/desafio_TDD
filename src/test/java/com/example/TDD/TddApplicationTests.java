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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TddApplicationTests {

	TreeMap<Ingrediente, Integer> novoEstoqueTreeMap = new TreeMap<>();
	Armazem estoqueArmazem = new Armazem(novoEstoqueTreeMap);

	@Test
	void contextLoad() {
	}

	//TODO - setup() para iniciar variáveis, objetos, com @BeforeEach
	//TODO - separar testes BONS dos RUINS
	//TODO - NOMENCLATURAS - nomeDoMetodoTestado_WhenCondicacaoTestada_ShouldComportamentoEsperado

	@Test
	public void testCadastrarIngredienteEmEstoque() throws IllegalArgumentException {
		Base novaBase = new Base(TipoBase.Iorgute);

		estoqueArmazem.cadastrarIngredienteEmEstoque(novaBase);
		assertEquals(1, estoqueArmazem.getEstoqueTreeMap().size());
		//TODO - verificar a regra da quantidade setada com 0

	}

	@Test
	public void testCadastrarIngredienteEmEstoqueErro() throws IllegalArgumentException {

		Throwable erro = assertThrows(IllegalArgumentException.class, () -> {
			Fruta fruta = new Fruta(TipoFruta.Abacate);
			estoqueArmazem.cadastrarIngredienteEmEstoque(fruta);
			estoqueArmazem.cadastrarIngredienteEmEstoque(fruta);
		});

		assertEquals(erro.getMessage(), "Ingrediente já cadastrado");
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
			//TODO - assertTrue or assertFalse
			//TODO - comparar a quantidade do item inserido

			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, novaQtd);
			assertEquals(true, estoqueArmazem.getEstoqueTreeMap().containsValue(quantidade + novaQtd));

			estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(toppingErro, novaQtd);

		} catch (IngredienteNaoEncontradoException e1) {
			assertEquals("Ingrediente não encontrado", e1.getMessage());
			try {
				estoqueArmazem.adicionarQuantidadeDoIngredienteEmEstoque(novoTopping, qtdErro);
			} catch (QuantidadeInvalidaException e2) {
				assertEquals("Quantidade inválida", e2.getMessage());
			}
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

		} catch (IngredienteNaoEncontradoException e1) {
			assertEquals("Ingrediente não encontrado", e1.getMessage());
			try {
				estoqueArmazem.reduzirQuantidadeDoIngredienteEmEstoque(novoTopping, qtdErroNegativa);
			} catch (QuantidadeInvalidaException e2) {
				assertEquals("Quantidade inválida", e2.getMessage());
				try {
					estoqueArmazem.reduzirQuantidadeDoIngredienteEmEstoque(novoTopping, qtdErro);
				} catch (EstoqueQuantidadeException e3) {
					assertEquals("Estoque com quantidade baixa", e3.getMessage());
				}
			}
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
