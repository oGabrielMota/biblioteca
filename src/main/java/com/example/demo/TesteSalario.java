package com.example.demo;

public class TesteSalario {
	public static void main(String[] args) {
		Salario salario = new Salario();
		System.out.println("O salário esta com 10% de aumento: " + (salario.calculaAumento(1000) == 1100));
		System.out.println("O salário esta com 10% de aumento: " + (salario.calculaAumento(1500) == 1650.01));
		System.out.println("O salário esta com 10% de aumento: " + (salario.calculaAumento(2036.54) == 2240.19));
	}
}
