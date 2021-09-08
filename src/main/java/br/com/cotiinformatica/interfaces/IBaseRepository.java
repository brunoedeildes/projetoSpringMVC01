package br.com.cotiinformatica.interfaces;

import java.util.List;

public interface IBaseRepository<T> {
	void inserir(T obj) throws Exception;

	void alterar(T obj) throws Exception;

	void excluir(T obj) throws Exception;

	List<T> consultar() throws Exception;

	T obterPorId(Integer id) throws Exception;
}
