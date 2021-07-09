package com.org.generation.lojagame.controller;

import java.util.List;
import java.util.Optional;

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

import com.org.generation.lojagame.model.Categoria;
import com.org.generation.lojagame.repository.CategoriaRepository;

@RestController
@RequestMapping("/lojagames")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
		
		}
	
	@GetMapping("ifelse/{id}")
	public ResponseEntity<Categoria> GetByIfElse(@PathVariable long id){
		
		Optional<Categoria> categoria = repository.findById(id);
		if(categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}
		return ResponseEntity.notFound().build();	
		}
	
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable String descricao){
		return  ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
				}
	
	
	
	//post put delete
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
	

