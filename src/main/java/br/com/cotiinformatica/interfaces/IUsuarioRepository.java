package br.com.cotiinformatica.interfaces;

import br.com.cotiinformatica.entities.Usuario;

public interface IUsuarioRepository extends IBaseRepository<Usuario> {

	/*
	 * M�todo para consultar 1 usu�rio na base de dados
	 * atraves do email informado
	 */
	Usuario obter(String email) throws Exception;
	
	/*
	 * M�todo para consultar 1 usu�rio na base de dados
	 * atraves do email e da senha
	 */
	Usuario obter(String email, String senha) throws Exception;
}
