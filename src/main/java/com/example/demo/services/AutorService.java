package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AutorEntity;
import com.example.demo.exceptions.BadRequestBussinessException;
import com.example.demo.exceptions.NotFoundBussinessException;
import com.example.demo.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	@Transactional
	public AutorEntity cria(AutorEntity autor) {

		boolean autorExistente = this.existeAutorPeloNome(autor.getNome());

		if (autorExistente) {
			throw new BadRequestBussinessException("Autor existente");
		}

		return autorRepository.save(autor);
	}

	@Transactional
	public AutorEntity alterar(AutorEntity autor) {

		if (autor.getId() == null || autor.getId() <= 0) {
			throw new BadRequestBussinessException("O campo Id é obrigatório para alterar um autor!");
		}

		boolean autorExistente = this.existeAutorPeloNomeQueNaoSouEu(autor.getNome(), autor.getId());

		if (autorExistente) {
			throw new BadRequestBussinessException("Autor existente");
		}

		this.buscaPeloId(autor.getId());

		return autorRepository.save(autor);
	}

	public AutorEntity buscaPeloId(Long id) {
		return autorRepository.findById(id)
				.orElseThrow(() -> new NotFoundBussinessException("Não foi encontrado o autor pelo id: " + id));
	}

	public List<AutorEntity> listaTodos() {
		return autorRepository.findAll();
	}

	public boolean existeAutorPeloNome(String nome) {
		Optional<AutorEntity> autor = autorRepository.findByNome(nome);
		return autor.isPresent();
	}

	public boolean existeAutorPeloNomeQueNaoSouEu(String nome, Long id) {
		Optional<AutorEntity> autor = autorRepository.findByNomeAndIdNot(nome, id);
		return autor.isPresent();
	}
}
