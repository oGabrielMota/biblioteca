package com.example.demo.constrollers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
class AutorInserirControllerTest {

	@Autowired
	private MyMvcMock mvc;

	private AutorInput autor;

	@BeforeEach
	void setUp() throws Exception {
		this.autor = new AutorInput();
		autor.setNome("Jefferson Zuanon");
		autor.setBiografia("O cara e demais");
	}

	@Test
	void quando_InserirUmUsuario_Entao_RetornaOk() throws Exception {

		ResultActions result = mvc.created("/api/autores", autor);
		result.andExpect(jsonPath("id").value(1));
		result.andExpect(jsonPath("nome").value(autor.getNome()));
		result.andExpect(jsonPath("biografia").value(autor.getBiografia()));

	}

	@Test
	void quando_InserirUsuarioDuplicado_Entao_RetornaErroUsuarioJaCadastrado() throws Exception {

		mvc.created("/api/autores", autor);

		ResultActions result2 = mvc.createdWithBadRequest("/api/autores", autor);
		result2.andExpect(jsonPath("message").value("Autor existente"));

	}

	@Test
	void quando_InserirUsuarioSemNome_Entao_RetornaErroNomeObrigatorio() throws Exception {

		this.autor.setNome(null);
		ResultActions result = mvc.createdWithBadRequest("/api/autores", autor);
		result.andExpect(jsonPath("$.campos[?(@.message == 'O campo Nome é obrigatório!')]").exists());
	}

	@Test
	void quando_InserirUsuarioSemNomePeloService_Entao_RetornaErroNomeObrigatorio() throws Exception {

		this.autor.setNome(null);
		ResultActions result = mvc.createdWithBadRequest("/api/autores", autor);
		result.andExpect(jsonPath("$.campos[?(@.message == 'O campo Nome é obrigatório!')]").exists());
	}

	@Test
	void quando_InserirUsuarioComNomeVazio_Entao_RetornaErroNomeObrigatorioETamanhoMinimoObrigatorio()
			throws Exception {

		this.autor.setNome("");
		ResultActions result = mvc.createdWithBadRequest("/api/autores", autor);

		result.andExpect(jsonPath("$.campos[?(@.message == 'O campo Nome é obrigatório!')]").exists());
		result.andExpect(
				jsonPath("$.campos[?(@.message == 'O campo Nome deve ter no máximo 100 caracteres!')]").exists());
	}

	@Test
	void quando_InserirUsuarioComBiografiaVazia_Entao_RetornaErroBiografiaObrigatoriaETamanhoMinimoObrigatorio()
			throws Exception {

		this.autor.setBiografia("");
		ResultActions result = mvc.createdWithBadRequest("/api/autores", autor);

		result.andExpect(jsonPath("$.campos[?(@.message == 'O campo Biografia é obrigatório!')]").exists());
		result.andExpect(
				jsonPath("$.campos[?(@.message == 'O campo Biografia deve ter no máximo 1000 caracteres!')]").exists());
	}

}
