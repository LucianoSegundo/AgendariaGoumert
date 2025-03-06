package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Contato;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Usuario;

public final class UsuarioRepository implements Repositorio<Usuario, Long> {

	private ContatoRepository contatoRepo = new ContatoRepository();
	@Override
	public void inserir(Usuario z) throws SQLException {

		String query = "insert into usuario (usuario, nome, senha, email)"
				+"values(?,?,?,?);";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, z.getUsuario());
		pstm.setString(2, z.getNome());
		pstm.setString(3, z.getSenha());
		pstm.setString(4, z.getEmail());
		
		pstm.execute();
		
		
	}

	@Override
	public void alterar(Usuario z) throws SQLException {
		
		String sql = "update usuario set nome=?, senha=?, email=?"
				+ "where idusuario = "+ z.getId()+";";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, z.getNome());
		pstm.setString(2, z.getSenha());
		pstm.setString(3, z.getEmail());
		
		pstm.execute();
		
	}

	@Override
	public Usuario ler(Long k) throws SQLException {
		String query = "select * from usuario where idusuario ="+ k+";";
		
		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();
		
		Usuario resposta = null;
		
		if(resultado.next()) {
		resposta = montarUsuario(resultado);
		}
		
		return resposta;
	}
	
	public Usuario ler(String usuario) throws SQLException {
		
		String query = "select * from usuario where usuario = '"+ usuario+"';";
		
		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();
		
		Usuario resposta = null;
		
		if(resultado.next()) {
			resposta = montarUsuario(resultado);
			}
		
		
		return resposta;
	}

	@Override
	public void delete(Long k) throws SQLException {
		
		String sql = "delete from usuario where idusuario = "+ k+";";
		
		List<Contato> contatos = contatoRepo.listar(k);
		
		for (Contato contato : contatos) {
			contatoRepo.delete(contato.getId());
			
		}		
		
		ConnectionManager.getCurrentConnection().prepareStatement(sql).execute();
		
	}

	private  Usuario montarUsuario(ResultSet resultado) throws SQLException {
		Usuario resposta = new Usuario();
			
			resposta.setId(resultado.getLong("idusuario"));
			resposta.setUsuario(resultado.getString("usuario"));
			resposta.setNome(resultado.getString("nome"));
			resposta.setSenha(resultado.getString("senha"));
			resposta.setEmail(resultado.getString("email"));
			
			List<Contato> contatos = contatoRepo.listar(resposta.getId());
			resposta.setContatos(contatos);
		
		return resposta;
	}


	@Override
	public List<Usuario> listar(Long k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
