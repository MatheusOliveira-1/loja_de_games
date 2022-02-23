package com.generation.minhalojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.minhalojadegames.model.Produto;

@Repository
public interface ProdutoRepoitory extends JpaRepository <Produto, Long> {
	
	List <Produto> findAllByNomeContainingIgnoreCase(String nome);

}
