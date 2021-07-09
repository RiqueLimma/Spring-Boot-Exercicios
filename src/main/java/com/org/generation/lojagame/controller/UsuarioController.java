package com.org.generation.lojagame.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.generation.lojagame.model.Usuario;
import com.org.generation.lojagame.model.UsuarioLogin;
import com.org.generation.lojagame.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticationUsuario(@RequestBody Optional<UsuarioLogin> usuario) {
		return usuarioService.logarUsuario(usuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
		Optional<Usuario> novoUsuario = usuarioService.cadastrarUsuario(usuario);
		try {
				return ResponseEntity.ok(novoUsuario.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
		Optional<Usuario> updateUsuario = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(updateUsuario.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
