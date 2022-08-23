package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.LivroEntity;
import com.example.demo.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Transactional
	public LivroEntity cria(LivroEntity livro) {
		return livroRepository.save(livro);
	}

	@Transactional
	public LivroEntity alterar(LivroEntity livro) {
		return livroRepository.save(livro);
	}

	@Transactional
	public void remover(LivroEntity livro) {
		livroRepository.delete(livro);
	}

	public LivroEntity buscaPeloId(Long id) {
		return livroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi encontrado o livro pelo id: " + id));
	}

	public List<LivroEntity> listaTodos() {
		return livroRepository.findAll();
	}

	public List<LivroEntity> listaLivrosPeloAutor(Long idAutor) {
		return livroRepository.findAllByAutoresId(idAutor);
	}
}
