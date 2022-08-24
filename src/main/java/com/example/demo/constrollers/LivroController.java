package com.example.demo.constrollers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converts.LivroConvert;
import com.example.demo.dto.inputs.LivroInput;
import com.example.demo.dto.outputs.LivroOutput;
import com.example.demo.entities.AutorEntity;
import com.example.demo.entities.LivroEntity;
import com.example.demo.services.AutorService;
import com.example.demo.services.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Livros")
@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private LivroConvert livroConvert;

	@Operation( summary = "Cria livro", description = "Cadastra novo livro")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public LivroOutput criaLivro(@Valid @RequestBody @Parameter(description= "Representação de um livro") LivroInput livro) {
		LivroEntity livroEntity = livroConvert.inputToEntity(livro);

		convertAutores(livro, livroEntity);

		LivroEntity livroCriado = livroService.cria(livroEntity);
		return livroConvert.entityToOutput(livroCriado);
	}

	@Operation( summary = "Altera livro")
	@PutMapping("/{id}")
	public LivroOutput alteraLivro(@Parameter(description = "Id do Livro", example ="1") @PathVariable Long id, @Valid @RequestBody LivroInput livroInput) {
		LivroEntity livroEntity = livroService.buscaPeloId(id);
		livroConvert.copyDataInputToEntity(livroInput, livroEntity);

		convertAutores(livroInput, livroEntity);

		LivroEntity livroAlterado = livroService.alterar(livroEntity);
		return livroConvert.entityToOutput(livroAlterado);
	}

	@Operation( summary = "Busca livro pelo ID")
	@GetMapping("/{id}")
	public LivroOutput buscaLivroPorId(@Parameter(description = "Id do Livro", example ="1") @PathVariable Long id) {
		LivroEntity livroEntity = livroService.buscaPeloId(id);
		return livroConvert.entityToOutput(livroEntity);
	}

	@Operation( summary = "Lista todos os livros")
	@GetMapping
	public List<LivroOutput> listaLivros() {
		List<LivroEntity> listaTodos = livroService.listaTodos();
		return livroConvert.entityToOutput(listaTodos);
	}

	@Operation( summary = "Deleta livro")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeLivro( @Parameter(description = "Id do Livro", example ="1") @PathVariable Long id) {
		LivroEntity livroEntity = livroService.buscaPeloId(id);
		livroService.remover(livroEntity);
	}

	@Operation( summary = "Converte autores")
	private void convertAutores(LivroInput livro, LivroEntity livroEntity) {
		List<AutorEntity> autores = new ArrayList<>();
		for (Long autorId : livro.getAutoresIds()) {
			autores.add(autorService.buscaPeloId(autorId));
		}
		livroEntity.setAutores(autores);
	}
}
