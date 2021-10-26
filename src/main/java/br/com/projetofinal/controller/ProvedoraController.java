package br.com.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.beans.FiltroProvedoraDTO;
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
	
	@GetMapping("/provedora/{cod}")
	public ResponseEntity<Provedora> getProvedora(@PathVariable int cod){
		Provedora provedora = dao.findById(cod).orElse(null);
		if(provedora==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(provedora);
	}
	
	@GetMapping("/provedoras/filter")
	public ResponseEntity<List<Provedora>> filterByName(@RequestParam(name="nome", required=true)String nome,@RequestParam(name="site", required=false) String site){
		
		if(site != null) {
			//listagem por site
			System.out.println(site);
		}
		
		List<Provedora> retorno = (List<Provedora>) dao.findByNomeLike(nome);
		if(retorno.size()==0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping("/provedoras/search")
	public ResponseEntity<List<Provedora>> filterByFiltro(@RequestBody FiltroProvedoraDTO filtro){
		
		if(filtro.getNome() == null) {
			List<Provedora> retorno = (List<Provedora>) dao.findByFundacaoBetween(filtro.getDataInicio(), filtro.getDataFim());
			if(retorno.size()==0) {
				return ResponseEntity.status(404).build();
			}
			return ResponseEntity.ok(retorno);
		} else {
			List<Provedora> retorno = (List<Provedora>) dao.findByFundacaoBetweenAndNomeLike(filtro.getDataInicio(), filtro.getDataFim(),filtro.getNome());
			if(retorno.size() == 0) {
				return ResponseEntity.status(404).build();
			}
			return ResponseEntity.ok(retorno);
		}
	
	}
}
