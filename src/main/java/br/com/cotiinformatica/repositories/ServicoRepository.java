package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Profissional;
import br.com.cotiinformatica.entities.Servico;
import br.com.cotiinformatica.interfaces.IServicoRepository;

public class ServicoRepository implements IServicoRepository {

	//atributo
	private JdbcTemplate jdbcTemplate;
	
	//construtor utilizado para receber o DataSource (configurações)
	//e inicializar o atributo jdbcTemplate
	public ServicoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void inserir(Servico obj) throws Exception {

		String sql = "insert into servico(nome, descricao, tempoatendimento, preco, idprofissional) values(?, ?, ?, ?, ?)";
		
		Object[] params = {
			obj.getNome(),
			obj.getDescricao(),
			obj.getTempoAtendimento(),
			obj.getPreco(),
			obj.getProfissional().getIdProfissional()
		};
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void alterar(Servico obj) throws Exception {

		String sql = "update servico set nome = ?, descricao = ?, tempoatendimento = ?, preco = ?, idprofissional = ? where idservico = ?";
		
		Object[] params = {
			obj.getNome(),
			obj.getDescricao(),
			obj.getTempoAtendimento(),
			obj.getPreco(),
			obj.getProfissional().getIdProfissional(),
			obj.getIdServico()
		};
		
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public void excluir(Servico obj) throws Exception {

		String sql = "delete from servico where idservico = ?";
		
		Object[] params = {
			obj.getIdServico()
		};
		
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public List<Servico> consultar() throws Exception {

		String sql = "select "
				+ "	   s.idservico, "
				+ "    s.nome, "
				+ "    s.descricao, "
				+ "    s.tempoatendimento, "
				+ "    s.preco, "
				+ "    p.idprofissional, "
				+ "    p.nome as nomeprofissional, "
				+ "    p.cpf, "
				+ "    p.telefone, "
				+ "    p.email, "
				+ "    p.observacoes "
				+ "from servico s "
				+ "inner join profissional p "
				+ "on p.idprofissional = s.idprofissional";
		
		List<Servico> lista = jdbcTemplate.query(sql, new RowMapper<Servico>() {

			@Override
			public Servico mapRow(ResultSet rs, int rowNum) throws SQLException {

				Servico servico = new Servico();
				servico.setProfissional(new Profissional());
				
				servico.setIdServico(rs.getInt("idservico"));
				servico.setNome(rs.getString("nome"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setTempoAtendimento(rs.getInt("tempoatendimento"));
				servico.setPreco(rs.getDouble("preco"));
				
				servico.getProfissional().setIdProfissional(rs.getInt("idprofissional"));
				servico.getProfissional().setNome(rs.getString("nomeprofissional"));
				servico.getProfissional().setCpf(rs.getString("cpf"));
				servico.getProfissional().setTelefone(rs.getString("telefone"));
				servico.getProfissional().setEmail(rs.getString("email"));
				servico.getProfissional().setObservacoes(rs.getString("observacoes"));
				
				return servico;
			}			
		});
		
		return lista;
	}

	@Override
	public Servico obterPorId(Integer id) throws Exception {

		String sql = "select * from servico where idservico = ?";
		
		Object[] params = {
			id //parametro da consulta (id do serviço)	
		};
		
		List<Servico> lista = jdbcTemplate.query(sql, params, new RowMapper<Servico>() {

			@Override
			public Servico mapRow(ResultSet rs, int rowNum) throws SQLException {

				Servico servico = new Servico();
				servico.setProfissional(new Profissional());
				
				servico.setIdServico(rs.getInt("idservico"));
				servico.setNome(rs.getString("nome"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setTempoAtendimento(rs.getInt("tempoatendimento"));
				servico.setPreco(rs.getDouble("preco"));
				servico.getProfissional().setIdProfissional(rs.getInt("idprofissional"));
				
				return servico;
			}			
		});
		
		if(lista.size() == 1) //se foi encontrado
			return lista.get(0); //retornando o registro
		else
			return null; //vazio
	}

	@Override
	public List<Servico> consultarPorNome(String nome) throws Exception {

		String sql = "select "
				+ "	   s.idservico, "
				+ "    s.nome, "
				+ "    s.descricao, "
				+ "    s.tempoatendimento, "
				+ "    s.preco, "
				+ "    p.idprofissional, "
				+ "    p.nome as nomeprofissional, "
				+ "    p.cpf, "
				+ "    p.telefone, "
				+ "    p.email, "
				+ "    p.observacoes "
				+ "from servico s "
				+ "inner join profissional p "
				+ "on p.idprofissional = s.idprofissional "
				+ "where s.nome like ?";
		
		Object[] params = {
			"%" + nome + "%"
		};
		
		List<Servico> lista = jdbcTemplate.query(sql, params, new RowMapper<Servico>() {

			@Override
			public Servico mapRow(ResultSet rs, int rowNum) throws SQLException {

				Servico servico = new Servico();
				servico.setProfissional(new Profissional());
				
				servico.setIdServico(rs.getInt("idservico"));
				servico.setNome(rs.getString("nome"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setTempoAtendimento(rs.getInt("tempoatendimento"));
				servico.setPreco(rs.getDouble("preco"));
				
				servico.getProfissional().setIdProfissional(rs.getInt("idprofissional"));
				servico.getProfissional().setNome(rs.getString("nomeprofissional"));
				servico.getProfissional().setCpf(rs.getString("cpf"));
				servico.getProfissional().setTelefone(rs.getString("telefone"));
				servico.getProfissional().setEmail(rs.getString("email"));
				servico.getProfissional().setObservacoes(rs.getString("observacoes"));
				
				return servico;
			}			
		});
		
		return lista;
	}

}
