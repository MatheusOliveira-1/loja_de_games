package com.generation.minhalojadegames.controller;

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
import org.springframework.web.server.ResponseStatusException;

import com.generation.minhalojadegames.model.Categoria;
import com.generation.minhalojadegames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategoria() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<Categoria> findByIDCategoria(@PathVariable Long id) {
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> findByDescricaoCategoria(@PathVariable String descricao) {
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));

	}

	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) {
		if (categoriaRepository.existsByNomeContainingIgnoreCase(categoria.getNome())) {
			return new ResponseEntity("A cateogoria '" + categoria.getNome() + "' já existe.", HttpStatus.BAD_REQUEST);

		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
		}
	}

	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) {
		if (categoriaRepository.existsById(categoria.getId())) {
			return categoriaRepository.findById(categoria.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(categoriaRepository.save(categoria)))
					.orElse(ResponseEntity.notFound().build()); 
		
		} else {
			return new ResponseEntity("A categoria com id '" + categoria.getId() + "' não existe.", HttpStatus.NOT_FOUND);
		
		}
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deleteCategoria (@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resp -> {				
				categoriaRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
						.orElse(ResponseEntity.notFound().build());
	}
	
	
}
