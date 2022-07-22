package com.example.TDD;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TddApplicationTests<Ingrediente> {

//	@Test
//	void contextLoads() {
//	}

	@Test
	public void testCadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
		try {

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ingrediente já cadastrado");
		}
	}

	@Test
	public void testDescadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
		try {

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ingrediente não encontrado");
		}
	}

	@Test
	public void testAdicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
		try {

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ingrediente não encontrado ou quantidade inválida");
		}
	}

	@Test
	public void testReduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
		try {

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ingrediente não encontrado ou quantidade inválida");
		}
	}

	@Test
	public void testConsultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
		try {

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ingrediente não encontrado");
		}
	}

}
