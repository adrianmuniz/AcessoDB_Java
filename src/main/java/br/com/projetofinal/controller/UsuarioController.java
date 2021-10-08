package br.com.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.beans.Usuario;
import br.com.projetofinal.dao.UsuarioDAO;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		
		List<Usuario> retorno = (List<Usuario>) dao.findAll();
		
		return ResponseEntity.ok(retorno);
	}
}
