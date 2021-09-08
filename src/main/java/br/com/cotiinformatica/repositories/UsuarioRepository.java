package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;

public class UsuarioRepository implements IUsuarioRepository {

	// atributo
	private JdbcTemplate jdbcTemplate;

	// método construtor para inicializar o atributo
	public UsuarioRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void inserir(Usuario obj) throws Exception {

		String sql = "insert into usuario(nome, email, senha) values(?, ?, md5(?))";

		Object[] params = { obj.getNome(), obj.getEmail(), obj.getSenha() };

		jdbcTemplate.update(sql, params);
	}

	@Override
	public void alterar(Usuario obj) throws Exception {

		String sql = "update usuario set nome = ?, email = ?, senha = md5(?) where idusuario = ?";

		Object[] params = { obj.getNome(), obj.getEmail(), obj.getSenha(), obj.getIdUsuario() };

		jdbcTemplate.update(sql, params);
	}

	@Override
	public void excluir(Usuario obj) throws Exception {

		String sql = "delete from usuario where idusuario = ?";

		Object[] params = { obj.getIdUsuario() };

		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Usuario> consultar() throws Exception {

		String sql = "select * from usuario";

		List<Usuario> lista = jdbcTemplate.query(sql, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}
		});

		return lista;
	}

	@Override
	public Usuario obterPorId(Integer id) throws Exception {

		String sql = "select * from usuario where idusuario = ?";

		Object[] params = { id };

		List<Usuario> lista = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}
		});

		if (lista.size() == 1)
			return lista.get(0);
		else
			return null;
	}

	@Override
	public Usuario obter(String email) throws Exception {

		String sql = "select * from usuario where email = ?";

		Object[] params = { email };

		List<Usuario> lista = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}
		});

		if (lista.size() == 1)
			return lista.get(0);
		else
			return null;
	}

	@Override
	public Usuario obter(String email, String senha) throws Exception {

		String sql = "select * from usuario where email = ? and senha = md5(?)";

		Object[] params = { email, senha };

		List<Usuario> lista = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}
		});

		if (lista.size() == 1)
			return lista.get(0);
		else
			return null;
	}

}


