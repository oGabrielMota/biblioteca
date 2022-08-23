package com.example.demo.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.inputs.AutorInput;
import com.example.demo.dto.outputs.AutorOutput;
import com.example.demo.entities.AutorEntity;

@Component
public class AutorConvert {

	@Autowired
	private ModelMapper model;

	public AutorOutput entityToOutput(AutorEntity autorEntity) {
		return model.map(autorEntity, AutorOutput.class);
	}

	public List<AutorOutput> entityToOutput(List<AutorEntity> autoresEntity) {
		return autoresEntity.stream().map(autor -> this.entityToOutput(autor)).collect(Collectors.toList());
	}

	public AutorEntity inputToEntity(AutorInput autorInput) {
		return model.map(autorInput, AutorEntity.class);
	}

	public void copyDataInputToEntity(AutorInput autorInput, AutorInput autorEntity) {
		model.map(autorInput, autorEntity);
	}

}
