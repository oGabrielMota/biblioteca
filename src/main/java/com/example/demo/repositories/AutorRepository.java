package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.AutorEntity;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

	Optional<AutorEntity> findByNome(String nome);

	Optional<AutorEntity> findByNomeAndIdNot(String nome, Long id);

}
