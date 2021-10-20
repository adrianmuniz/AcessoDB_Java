package br.com.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.beans.Provedora;
import br.com.projetofinal.dao.ProvedoraDAO;

@RestController
@CrossOrigin("*")
public class ProvedoraController {
	@Autowired
	private ProvedoraDAO dao;
	
	@PostMapping("/novaprovedora")
	public ResponseEntity<Provedora> add(@RequestBody Provedora novaprovedora){
		try {
			dao.save(novaprovedora);
			return ResponseEntity.ok(novaprovedora);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
}
