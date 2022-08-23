package com.example.demo.dto.inputs;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorInput {

	@NotEmpty(message = "O campo Nome é obrigatório!")
	@Length(message = "O campo Nome deve ter no máximo 100 caracteres!", min = 1, max = 100)
	private String nome;

	@Length(message = "O campo Biografia deve ter no máximo 1000 caracteres!", min = 1, max = 1000)
	@NotEmpty(message = "O campo Biografia é obrigatório!")
	private String biografia;
}
