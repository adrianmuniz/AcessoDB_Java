package br.com.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/provedoras")
	public ResponseEntity<List<Provedora>> getAll() {
		List<Provedora> provedoras = (List<Provedora>) dao.findAll();
		if(provedoras.size()==0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(provedoras);
	}
}
