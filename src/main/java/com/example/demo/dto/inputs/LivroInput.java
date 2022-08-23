package com.example.demo.dto.inputs;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroInput {

	@NotEmpty(message = "O campo Título é obrigatório!")
	@Length(message = "O campo Ano de Lançamento deve ter no 4 dígitos!", min = 1, max = 200)
	private String titulo;

	@NotNull(message = "O campo Ano de Lançamento é obrigatório!")
	@Min(message = "O ano de lançamento de ter 4 dígitos!", value = 1000)
	@Max(message = "O ano de lançamento de ter 4 dígitos!", value = 9999)
	private Integer anoLancamento;

	@NotEmpty(message = "Deve haver pelo menos um Autor!")
	private List<Long> autoresIds;
}
