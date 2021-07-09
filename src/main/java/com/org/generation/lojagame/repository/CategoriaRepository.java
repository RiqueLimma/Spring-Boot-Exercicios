package com.org.generation.lojagame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.generation.lojagame.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List<Categoria> findAllByDescricaoContainingIgnoreCase (String descricao);
	
	
	
}