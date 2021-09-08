package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Profissional;
import br.com.cotiinformatica.interfaces.IProfissionalRepository;

public class ProfissionalRepository implements IProfissionalRepository {

	//atributo
	private JdbcTemplate jdbcTemplate;
	
	//construtor para inicializar o atributo jdbcTemplate
	public ProfissionalRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void inserir(Profissional obj) throws Exception {

		String sql = "insert into profissional(nome, cpf, telefone, email, observacoes) values(?, ?, ?, ?, ?)";

		Object[] params = {
			obj.getNome(),
			obj.getCpf(),
			obj.getTelefone(),
			obj.getEmail(),
			obj.getObservacoes()
		};
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void alterar(Profissional obj) throws Exception {

		String sql = "update profissional set nome = ?, cpf = ?, telefone = ?, email = ?, observacoes = ? where idprofissional = ?";

		Object[] params = {
			obj.getNome(),
			obj.getCpf(),
			obj.getTelefone(),
			obj.getEmail(),
			obj.getObservacoes(),
			obj.getIdProfissional()
		};
		
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void excluir(Profissional obj) throws Exception {
		
		String sql = "delete from profissional where idprofissional = ?";

		Object[] params = {
			obj.getIdProfissional()
		};
		
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Profissional> consultar() throws Exception {
		
		String sql = "select * from profissional";
		
		List<Profissional> lista = jdbcTemplate.query(sql, new RowMapper<Profissional>() {

			@Override
			public Profissional mapRow(ResultSet rs, int rowNum) throws SQLException {

				Profissional profissional = new Profissional();
				
				profissional.setIdProfissional(rs.getInt("idprofissional"));
				profissional.setNome(rs.getString("nome"));
				profissional.setCpf(rs.getString("cpf"));
				profissional.setEmail(rs.getString("email"));
				profissional.setTelefone(rs.getString("telefone"));
				profissional.setObservacoes(rs.getString("observacoes"));
				
				return profissional;
			}			
		});
		
		return lista;
	}

	@Override
	public Profissional obterPorId(Integer id) throws Exception {

		String sql = "select * from profissional where idprofissional = ?";
		
		Object[] params = {
			id //parametro utilizado na busca (id do profissional)	
		};
		
		List<Profissional> lista = jdbcTemplate.query(sql, params, new RowMapper<Profissional>() {

			@Override
			public Profissional mapRow(ResultSet rs, int rowNum) throws SQLException {

				Profissional profissional = new Profissional();
				
				profissional.setIdProfissional(rs.getInt("idprofissional"));
				profissional.setNome(rs.getString("nome"));
				profissional.setCpf(rs.getString("cpf"));
				profissional.setEmail(rs.getString("email"));
				profissional.setTelefone(rs.getString("telefone"));
				profissional.setObservacoes(rs.getString("observacoes"));
				
				return profissional;
			}			
		});
		
		if(lista.size() == 1)
			return lista.get(0);
		else
			return null;
	}

	@Override
	public List<Profissional> consultarPorNome(String nome) throws Exception {

		String sql = "select * from profissional where nome like ?";
		
		Object[] params = {
			"%" + nome + "%"
		};
		
		List<Profissional> lista = jdbcTemplate.query(sql, params, new RowMapper<Profissional>() {

			@Override
			public Profissional mapRow(ResultSet rs, int rowNum) throws SQLException {

				Profissional profissional = new Profissional();
				
				profissional.setIdProfissional(rs.getInt("idprofissional"));
				profissional.setNome(rs.getString("nome"));
				profissional.setCpf(rs.getString("cpf"));
				profissional.setEmail(rs.getString("email"));
				profissional.setTelefone(rs.getString("telefone"));
				profissional.setObservacoes(rs.getString("observacoes"));
				
				return profissional;
			}			
		});
		
		return lista;
	}
}













