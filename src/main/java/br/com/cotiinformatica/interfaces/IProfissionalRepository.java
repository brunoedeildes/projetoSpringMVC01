package br.com.cotiinformatica.interfaces;

import java.util.List;

import br.com.cotiinformatica.entities.Profissional;

public interface IProfissionalRepository extends IBaseRepository<Profissional> {

	List<Profissional> consultarPorNome(String nome) throws Exception;

}
