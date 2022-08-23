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
class AutorAlterarControllerTest {

	@Autowired
	private MyMvcMock mvc;

	AutorInput autorInserir;
	AutorInput autorAlterar;

	@BeforeEach
	void setUp() throws Exception {
		this.autorInserir = new AutorInput();
		autorInserir.setNome("Jefferson Zuanon");
		autorInserir.setBiografia("O cara e demais");
		this.autorAlterar = new AutorInput();
		autorAlterar.setNome("Jefferson Zuanon Alterado");
		autorAlterar.setBiografia("O cara e demais Alterado");
	}

	@Test
	void quando_AutalizarUmUsuario_Entao_RetornaOk() throws Exception {

		mvc.created("/api/autores", autorInserir);

		ResultActions resultAlterar = mvc.update("/api/autores/1", autorAlterar);
		resultAlterar.andExpect(jsonPath("id").value(1));
		resultAlterar.andExpect(jsonPath("nome").value(autorAlterar.getNome()));
		resultAlterar.andExpect(jsonPath("biografia").value(autorAlterar.getBiografia()));
	}

	@Test
	void quando_AlterarUsuarioSemNome_Entao_RetornaErroNomeObrigatorio() throws Exception {

		mvc.created("/api/autores", autorInserir);

		this.autorAlterar.setNome(null);

		ResultActions resultAlterar = mvc.updateWithBadRequest("/api/autores/1", autorAlterar);
		resultAlterar.andExpect(jsonPath("$.campos[?(@.message == 'O campo Nome é obrigatório!')]").exists());
	}

	@Test
	void quando_AlterarUsuarioComNomeVazio_Entao_RetornaErroNomeObrigatorioETamanhoMinimoObrigatorio()
			throws Exception {

		mvc.created("/api/autores", autorInserir);

		this.autorAlterar.setNome("");

		ResultActions resultAlterar = mvc.updateWithBadRequest("/api/autores/1", autorAlterar);
		resultAlterar.andExpect(jsonPath("$.campos[?(@.message == 'O campo Nome é obrigatório!')]").exists());
		resultAlterar.andExpect(
				jsonPath("$.campos[?(@.message == 'O campo Nome deve ter no máximo 100 caracteres!')]").exists());

	}

	@Test
	void quando_AlterarUsuarioSemBiografia_Entao_RetornaErroBiografiaObrigatoria() throws Exception {

		mvc.created("/api/autores", autorInserir);

		this.autorAlterar.setBiografia(null);

		ResultActions resultAlterar = mvc.updateWithBadRequest("/api/autores/1", autorAlterar);
		resultAlterar.andExpect(jsonPath("$.campos[?(@.message == 'O campo Biografia é obrigatório!')]").exists());

	}

	@Test
	void quando_AlterarUsuarioComBiografiaVazia_Entao_RetornaErroBiografiaObrigatoriaETamanhoMinimoObrigatorio()
			throws Exception {

		mvc.created("/api/autores", autorInserir);

		this.autorAlterar.setBiografia("");

		ResultActions resultAlterar = mvc.updateWithBadRequest("/api/autores/1", autorAlterar);
		resultAlterar.andExpect(jsonPath("$.campos[?(@.message == 'O campo Biografia é obrigatório!')]").exists());
		resultAlterar.andExpect(
				jsonPath("$.campos[?(@.message == 'O campo Biografia deve ter no máximo 1000 caracteres!')]").exists());

	}

	@Test
	void quando_AlterarUsuarioDuplicado_Entao_RetornaErroUsuarioJaCadastrado() throws Exception {

		mvc.created("/api/autores", autorInserir);

		mvc.created("/api/autores", autorAlterar);

		ResultActions resultAlterar = mvc.updateWithBadRequest("/api/autores/2", autorInserir);
		resultAlterar.andExpect(jsonPath("message").value("Autor existente"));

	}
}
