package com.generation.minhalojadegames.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id_produto;
	
	@NotBlank(message = "O atributo nome é obrigatório")
	@Size(max = 255, message = "O nome deve ter no máximo 255 caractéres")
	private String nome_produto;
	
	@NotBlank
	private Date data_lancamento;
	
	@
	private BigDecimal preco;
	
	private Integer quantidade;

}
