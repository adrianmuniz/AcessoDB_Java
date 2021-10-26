package br.com.projetofinal.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.projetofinal.beans.Provedora;

public interface ProvedoraDAO extends CrudRepository<Provedora, Integer> {
	
	public List<Provedora> findByNomeLike(String nome);
	
	public List<Provedora> findByFundacaoBetween(Date dataInicio, Date dataFim);
	
	public List<Provedora> findByFundacaoBetweenAndNomeLike(Date dataInicio, Date dataFim, String nome);
}
