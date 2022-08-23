package com.example.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_livros")
@Getter
@Setter
public class LivroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, name = "titulo")
	private String titulo;

	@Column(length = 4, name = "ano_lancamento")
	private Integer anoLancamento;

	@ManyToMany
	@JoinTable(name = "tb_livros_autores", joinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"))
	private List<AutorEntity> autores;

}
