package br.com.cotiinformatica.interfaces;

import java.util.List;

import br.com.cotiinformatica.entities.Servico;

public interface IServicoRepository extends IBaseRepository<Servico> {

	List<Servico> consultarPorNome(String nome) throws Exception;
	
}


