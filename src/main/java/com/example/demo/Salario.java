package com.example.demo;

import java.math.BigDecimal;

public class Salario {

	public double calculaAumento(double valor) {
		BigDecimal salario = new BigDecimal(valor * 1.1);
		salario = salario.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return salario.doubleValue();
	}

	public Double aumentoGerente(Double valor) {
		if (valor == null) {
			return 0.0;
		} else if (valor > 0) {
			BigDecimal salario = new BigDecimal(valor * 2);
			salario = salario.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return salario.doubleValue();
		} else {
			throw new RuntimeException("");
		}

	}
}
