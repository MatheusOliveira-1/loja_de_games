package com.generation.minhalojadegames.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O atributo nome é obrigatório")
	@Size (max = 255, message = "O atributo nome deve conter no máximo 255 caractéres")
	private String nome;
	
	@NotBlank (message = "O atributo nome é obrigatório")
	@Size (max = 100, message = "O atributo descricao deve conter no máximo 500 caractéres")
	private String descricao;
	
	//decidi não colocar cascade intencionalmente
	@OneToMany(mappedBy = "categoria")
	@JsonIgnoreProperties("categoria")
	private List <Produto> produto;

	public Long getId_categorai() {
		return id;
	}

	public void setId_categorai(Long id_categorai) {
		this.id = id_categorai;
	}

	public String getNome_categoria() {
		return nome;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome = nome_categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
}
