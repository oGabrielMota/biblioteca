package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.exceptions.BadRequestBussinessException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_autores")
@Getter
@Setter
public class AutorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, name = "nome", unique = true)
	private String nome;

	@Column(length = 1000, name = "biografia")
	private String biografia;

	public void setNome(String nome) {
		if (nome == null || "".equals(nome)) {
			throw new BadRequestBussinessException("O campo Nome é obrigatório!");
		}

		if (nome.length() > 1000) {
			throw new BadRequestBussinessException("O campo Nome deve ter no máximo 100 caracteres!");
		}

		this.nome = nome;
	}

	public void setBiografia(String biografia) {
		if (biografia == null || "".equals(biografia)) {
			throw new BadRequestBussinessException("O campo Biografia é obrigatório!");
		}

		if (biografia.length() > 1000) {
			throw new BadRequestBussinessException("O campo Biografia deve ter no máximo 1000 caracteres!");
		}

		this.biografia = biografia;
	}
}
