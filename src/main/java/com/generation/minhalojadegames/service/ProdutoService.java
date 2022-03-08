package com.generation.minhalojadegames.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.minhalojadegames.model.Produto;
import com.generation.minhalojadegames.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> curtir(Long id){
		if (produtoRepository.existsById(id)) {
			
			Produto produto = produtoRepository.findById(id).get();
			
			produto.setCurtir(produto.getCurtir() + 1);
			
			return Optional.of(produtoRepository.save(produto));
		}
		
		return Optional.empty();
	}

}
