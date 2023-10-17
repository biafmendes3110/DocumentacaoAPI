package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Usuario;
import com.projetojpa.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;
		
		@Autowired
		public UsuarioController (UsuarioService usuarioService) {
			this.usuarioService = usuarioService;
		}
		@GetMapping("/{id}")
		@Operation(summary = "Localiza usuário por ID")
		public ResponseEntity <Usuario> buscaUsuarioIdControlId(@PathVariable Long id){
			Usuario usuario = usuarioService.buscaUsuarioId(id);
			if(usuario != null) {
				return ResponseEntity.ok(usuario);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@GetMapping("/")
		@Operation(summary = "Apresenta todos os usuários")
		public ResponseEntity<List<Usuario>> buscaTodasUsuarioControl() {
			List<Usuario> Usuario = usuarioService.buscaTodosUsuarios();

			return ResponseEntity.ok(Usuario);
		}
		@PostMapping("/")
		@Operation(summary = "Cadastra um usuário")
		public ResponseEntity<Usuario> salvaUsuarioControl(@RequestBody @Valid Usuario usuario){
			Usuario salvaUsuario = usuarioService.salvaUsuario(usuario);

			return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);

		}
		@PutMapping ("/{id}")
		@Operation(summary = "altera as informações do id")
		public ResponseEntity<Usuario> alterarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
			Usuario alterarUsuario = usuarioService.alterarUsuario(id,usuario);
			if (alterarUsuario  != null) {
				return ResponseEntity.ok(alterarUsuario);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{id}")
		@Operation(summary = "Apagar o id selecionado")
		public ResponseEntity<String> apagaUsuarioControl(@PathVariable Long id) {
			boolean apagar = usuarioService.apagarUsuario(id);
			if(apagar) {
				return ResponseEntity.ok().body("O produto foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

	}

