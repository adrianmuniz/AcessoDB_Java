package br.com.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.beans.Usuario;
import br.com.projetofinal.dao.UsuarioDAO;

@RestController
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		
		List<Usuario> retorno = (List<Usuario>) dao.findAll();
		if(retorno.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
		
		Usuario retorno = (Usuario)dao.findById(id).orElse(null);
		
		if(retorno == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
		try {
			if(usuario.getId() != 0) {
				boolean exists = dao.existsById(usuario.getId());
				if(!exists) 
					return ResponseEntity.status(404).build();
				
			}
			
			dao.save(usuario);
			
			return ResponseEntity.ok(usuario);
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario){
		Usuario retorno = (Usuario)dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		
		if(retorno == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(retorno);
	}
}
