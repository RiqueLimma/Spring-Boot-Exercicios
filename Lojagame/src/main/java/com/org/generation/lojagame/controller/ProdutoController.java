package com.org.generation.lojagame.controller;

import java.util.List;

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

import com.org.generation.lojagame.model.Produto;
import com.org.generation.lojagame.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/produto")

public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtorepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id) {
		return produtorepository.findById(id)
		   .map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}

	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome) {
		return ResponseEntity
			.ok(produtorepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	
	//post put delete
	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtorepository.save(produto));

	}

	@PutMapping
	public ResponseEntity<Produto> put(@RequestBody Produto produto) {
		return ResponseEntity.ok(produtorepository.save(produto));

	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		produtorepository.deleteById(id);
	}

}

