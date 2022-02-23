package com.generation.minhalojadegames.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório")
	@Size(max = 255, message = "O nome deve ter no máximo 255 caractéres")
	private String nome;
	
	@NotBlank
	private Date lancamento;
	
	@NotBlank
	private BigDecimal preco;
	
	@NotBlank
	private Integer quantidade;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId_produto() {
		return id;
	}

	public void setId_produto(Long id_produto) {
		this.id = id_produto;
	}

	public String getNome_produto() {
		return nome;
	}

	public void setNome_produto(String nome_produto) {
		this.nome = nome_produto;
	}

	public Date getData_lancamento() {
		return lancamento;
	}

	public void setData_lancamento(Date data_lancamento) {
		this.lancamento = data_lancamento;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
