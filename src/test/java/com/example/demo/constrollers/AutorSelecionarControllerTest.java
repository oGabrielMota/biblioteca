package com.example.demo.constrollers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.dto.inputs.AutorInput;
import com.example.demo.utils.MyMvcMock;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AutorSelecionarControllerTest {

	@Autowired
	private MyMvcMock mvc;

	AutorInput autor1;
	AutorInput autor2;

	@BeforeEach
	void setUp() throws Exception {
		this.autor1 = new AutorInput();
		autor1.setNome("Jefferson Zuanon");
		autor1.setBiografia("O cara e demais Um");
		this.autor2 = new AutorInput();
		autor2.setNome("Jefferson Zuanon Dois");
		autor2.setBiografia("O cara e demais Dois");
	}

	@Test
	void quando_SelecionarUmUsuarioPeloId_Entao_RetornaOk() throws Exception {

		mvc.created("/api/autores", autor1);

		ResultActions resultFind = mvc.find("/api/autores/1");
		resultFind.andExpect(jsonPath("id").value(1));
		resultFind.andExpect(jsonPath("nome").value(autor1.getNome()));
		resultFind.andExpect(jsonPath("biografia").value(autor1.getBiografia()));

	}

	@Test
	void quando_SelecionarUmUsuarioPeloIdInexistente_Entao_RetornaErro() throws Exception {

		ResultActions resultFind = mvc.findWithNotFound("/api/autores/1");
		resultFind.andExpect(jsonPath("message").value("Não foi encontrado o autor pelo id: 1"));

	}

	@Test
	void quando_AoSelecionarNenhumUsuario_Entao_RetornaOk() throws Exception {
		mvc.find("/api/autores");
	}

	@Test
	void quando_AoSelecionarMuitosUsuarios_Entao_RetornaOk() throws Exception {

		mvc.created("/api/autores", autor1);
		mvc.created("/api/autores", autor2);

		ResultActions resultFind = mvc.find("/api/autores");
		resultFind.andExpect(jsonPath("$",hasSize(2)));
		resultFind.andExpect(jsonPath("$.[?(@.id == 1)]").exists());
		resultFind.andExpect(jsonPath("$.[?(@.nome == '" + autor1.getNome() + "')]").exists());
		resultFind.andExpect(jsonPath("$.[?(@.biografia == '" + autor1.getBiografia() + "')]").exists());
		resultFind.andExpect(jsonPath("$.[?(@.id == 2)]").exists());
		resultFind.andExpect(jsonPath("$.[?(@.nome == '" + autor2.getNome() + "')]").exists());
		resultFind.andExpect(jsonPath("$.[?(@.biografia == '" + autor2.getBiografia() + "')]").exists());

	}

}
