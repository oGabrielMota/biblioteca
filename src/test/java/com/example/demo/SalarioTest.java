package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SalarioTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeAll");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("@BeforeEach");
	}
	
	@Test
	public void testaAumentoSalario1000Para1100() {
		Salario salario = new Salario();
		assertEquals(salario.calculaAumento(1000), 1100);
	}

	@Test
	public void testaAumentoSalario1500Para1650() {
		Salario salario = new Salario();
		assertEquals(salario.calculaAumento(1500), 1650);
	}

	@Test
	public void testaAumentoSalario2036_54Para2240_19() {
		Salario salario = new Salario();
		assertEquals(salario.calculaAumento(2036.54), 2240.19);
	}

	@Test
	public void testaAumentoSalario1000Para2000Gerente() {
		Salario salario = new Salario();
		assertEquals(2000, salario.aumentoGerente(1000.0));
	}

	@Test
	public void testaAumentoSalario1234_63Para2469_26erente() {
		Salario salario = new Salario();
		assertEquals(2469.26, salario.aumentoGerente(1234.63));
	}

	@Test
	public void testaAumentoSalario12345_67Para24691_34erente() {
		Salario salario = new Salario();
		assertEquals(24691.34, salario.aumentoGerente(12345.67));
	}

	@Test
	public void testaAumentoSalario1Para2erente() {
		Salario salario = new Salario();
		assertEquals(2, salario.aumentoGerente(1.0));
	}

	@Test
	public void testaAumentoSalarioParaValorNulo() {
		Salario salario = new Salario();
		assertEquals(0, salario.aumentoGerente(null));
	}

	@Test
	public void testaAumentoSalarioParaValorNegativo() {
		Salario salario = new Salario();
		assertThrows(RuntimeException.class, () -> salario.aumentoGerente(-1.0), "");
	}

}
