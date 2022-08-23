package com.example.demo.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.inputs.LivroInput;
import com.example.demo.dto.outputs.LivroOutput;
import com.example.demo.entities.LivroEntity;

@Component
public class LivroConvert {

	@Autowired
	private ModelMapper model;

	public LivroOutput entityToOutput(LivroEntity livroEntity) {
		return model.map(livroEntity, LivroOutput.class);
	}

	public List<LivroOutput> entityToOutput(List<LivroEntity> livrosEntity) {
		return livrosEntity.stream().map(livro -> this.entityToOutput(livro)).collect(Collectors.toList());
	}

	public LivroEntity inputToEntity(LivroInput livroInput) {
		return model.map(livroInput, LivroEntity.class);
	}

	public void copyDataInputToEntity(LivroInput livroInput, LivroEntity livroEntity) {
		model.map(livroInput, livroEntity);
	}

}
