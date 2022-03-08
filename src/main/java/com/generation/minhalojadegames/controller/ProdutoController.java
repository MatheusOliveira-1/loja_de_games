package com.generation.minhalojadegames.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.minhalojadegames.model.Produto;
import com.generation.minhalojadegames.repository.CategoriaRepository;
import com.generation.minhalojadegames.repository.ProdutoRepository;

@RestController
@RequestMapping ("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired CategoriaRepository categoriaRepository;
	
	@GetMapping("/all")
	public List<Produto> findAllProduto (){
		return produtoRepository.findAll();
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Produto> findByIDProduto(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto){
		return categoriaRepository.findById(produto.getCategoria().getId())
				.map(resposta -> {
					return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
				})
				.orElse(ResponseEntity.badRequest().build());
		
	}
	
	
	@PutMapping
	public ResponseEntity<Produto> putProduto (@Valid @RequestBody Produto produto){
		if (produtoRepository.existsById(produto.getId())){

			return categoriaRepository.findById(produto.getCategoria().getId())
					.map(resposta -> {
						return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
					})
					.orElse(ResponseEntity.badRequest().build());
		}		
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/maior-que/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMaiorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	
	@GetMapping("/menor-que/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMenorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));

	}
}
