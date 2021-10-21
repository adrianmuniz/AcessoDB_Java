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
import br.com.projetofinal.beans.Serie;
import br.com.projetofinal.dao.SerieDAO;

@RestController
@CrossOrigin("*")
public class SerieController {
	
	@Autowired
	private SerieDAO dao;
	
	@PostMapping("/novaserie")
	private ResponseEntity<Serie> add (@RequestBody Serie novaserie){
		try {
			dao.save(novaserie);
			return ResponseEntity.ok(novaserie);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@GetMapping("/series")
	public ResponseEntity<List<Serie>> getAll() {
		List<Serie> series = (List<Serie>) dao.findAll();
		if(series.size()==0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(series);
	}
}
