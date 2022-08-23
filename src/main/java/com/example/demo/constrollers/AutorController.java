package com.example.demo.constrollers;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converts.AutorConvert;
import com.example.demo.converts.LivroConvert;
import com.example.demo.dto.inputs.AutorInput;
import com.example.demo.dto.outputs.AutorOutput;
import com.example.demo.dto.outputs.LivroOutput;
import com.example.demo.entities.AutorEntity;
import com.example.demo.entities.LivroEntity;
import com.example.demo.services.AutorService;
import com.example.demo.services.LivroService;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "*")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorConvert autorConvert;

	@Autowired
	private LivroConvert livroConvert;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutorOutput criaAutor(@Valid @RequestBody AutorInput autor) throws URISyntaxException {
		AutorEntity autorEntity = autorConvert.inputToEntity(autor);
		AutorEntity autorCriado = autorService.cria(autorEntity);
		return autorConvert.entityToOutput(autorCriado);
	}

	@PutMapping("/{id}")
	public AutorOutput alteraAutor(@PathVariable Long id, @Valid @RequestBody AutorInput autorInput) {
		AutorEntity autorEntity = autorConvert.inputToEntity(autorInput);
		autorEntity.setId(id);
		AutorEntity autorAlterado = autorService.alterar(autorEntity);
		return autorConvert.entityToOutput(autorAlterado);
	}

	@GetMapping("/{id}")
	public AutorOutput buscaAutorPorId(@PathVariable Long id) {
		AutorEntity autorEntity = autorService.buscaPeloId(id);
		return autorConvert.entityToOutput(autorEntity);
	}

	@GetMapping
	public List<AutorOutput> listaAutores() {
		List<AutorEntity> listaTodos = autorService.listaTodos();
		return autorConvert.entityToOutput(listaTodos);
	}

	@GetMapping("/{idAutor}/livros")
	public List<LivroOutput> listaLivros(@PathVariable Long idAutor) {
		List<LivroEntity> listaTodos = livroService.listaLivrosPeloAutor(idAutor);
		return livroConvert.entityToOutput(listaTodos);
	}

}
