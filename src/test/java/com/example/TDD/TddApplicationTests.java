package com.example.TDD;

import com.example.armazem.Armazem;
import com.example.ingredientes.Base;
import com.example.ingredientes.Ingrediente;
import com.example.ingredientes.TipoBase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TddApplicationTests {

	TreeMap<Ingrediente, Integer> novoEstoqueTreeMap = new TreeMap<>();
	Armazem estoqueArmazem = new Armazem(novoEstoqueTreeMap);
	Base novaBase = new Base(TipoBase.Iorgute);

	@Test
	void contextLoad() {

	}

	@Test
	public void testCadastrarIngredienteEmEstoque() throws IllegalArgumentException {

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
//
//	@Test
//	public void testAdicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) throws IllegalArgumentException {
//		try {
//
//		} catch (IllegalArgumentException e) {
//			throw new IllegalArgumentException("Ingrediente não encontrado ou quantidade inválida");
//		}
//	}
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
