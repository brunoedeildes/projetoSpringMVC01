package br.com.cotiinformatica.interfaces;

import br.com.cotiinformatica.entities.Usuario;

public interface IUsuarioRepository extends IBaseRepository<Usuario> {

	/*
	 * Método para consultar 1 usuário na base de dados
	 * atraves do email informado
	 */
	Usuario obter(String email) throws Exception;
	
	/*
	 * Método para consultar 1 usuário na base de dados
	 * atraves do email e da senha
	 */
	Usuario obter(String email, String senha) throws Exception;
}
