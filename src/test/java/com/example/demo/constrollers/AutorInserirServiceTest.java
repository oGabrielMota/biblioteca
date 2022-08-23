package com.example.demo.constrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.example.demo.entities.AutorEntity;
import com.example.demo.services.AutorService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AutorInserirServiceTest {

	@Autowired
	private AutorService service;

	private AutorEntity autor;

	@BeforeEach
	void setUp() throws Exception {
		this.autor = new AutorEntity();
		autor.setNome("Jefferson Zuanon");
		autor.setBiografia("O cara e demais");
	}

	@Test
	void quando_InserirUmUsuario_Entao_RetornaOk() throws Exception {

		AutorEntity autorCriado = service.cria(autor);
		assertEquals(1, autorCriado.getId());
		assertEquals(autor.getNome(), autorCriado.getNome());
		assertEquals(autor.getBiografia(), autorCriado.getBiografia());

	}

	@Test
	void quando_InserirUsuarioDuplicado_Entao_RetornaErroUsuarioJaCadastrado() throws Exception {

		service.cria(autor);

		try {
			service.cria(autor);
		} catch (Exception e) {
			assertEquals("Autor existente", e.getMessage());
		}

	}

	@Test
	void quando_InserirUsuarioSemNome_Entao_RetornaErroNomeObrigatorio() throws Exception {
		try {
			this.autor.setNome(null);
		} catch (Exception e) {
			assertEquals("O campo Nome é obrigatório!", e.getMessage());
		}
	}

	@Test
	void quando_InserirUsuarioComNomeVazio_Entao_RetornaErroNomeObrigatorioETamanhoMinimoObrigatorio()
			throws Exception {

		try {
			this.autor.setNome("");
		} catch (Exception e) {
			assertEquals("O campo Nome é obrigatório!", e.getMessage());
		}
	}

	@Test
	void quando_InserirUsuarioSemBiografia_Entao_RetornaErroBiografiaObrigatoriaETamanhoMinimoObrigatorio()
			throws Exception {

		try {
			this.autor.setBiografia(null);
		} catch (Exception e) {
			assertEquals("O campo Biografia é obrigatório!", e.getMessage());
		}
	}

	@Test
	void quando_InserirUsuarioComBiografiaVazia_Entao_RetornaErroBiografiaObrigatoriaETamanhoMinimoObrigatorio()
			throws Exception {

		try {
			this.autor.setBiografia("");
		} catch (Exception e) {
			assertEquals("O campo Biografia é obrigatório!", e.getMessage());
		}
	}

}
