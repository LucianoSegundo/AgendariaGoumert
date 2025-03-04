package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Email;

public class EmailRepository implements Repositorio<Email, Long> {

	@Override
	public void inserir(Email email) throws SQLException {

		String query = "insert into email (email, idcontato)" + "values(?,?)" + ";";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, email.getEmail());
		pstm.setLong(2, email.getContato());

		pstm.execute();

	}

	@Override
	public void alterar(Email email) throws SQLException {

		String query = "update email set email=? where idemail =" + email.getId() + ";";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, email.getEmail());

		pstm.execute();

	}

	@Override
	public Email ler(Long idemail) throws SQLException {

		String query = "select * from email where idemail = " + idemail + ";";

		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();

		Email email = new Email();
		if (resultado.next()) {

			email = montarEmail(resultado);

		}
		return email;
	}

	public List<Email> listar(Long idcontato) throws SQLException {

		String query = "select * from email where idcontato = " + idcontato + ";";

		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();

		Email email = new Email();
		List<Email> listaEmail = new ArrayList<Email>();

		while (resultado.next()) {

			email = montarEmail(resultado);

			listaEmail.add(email);
		}
		return listaEmail;
	}

	@Override
	public void delete(Long idEmail) throws SQLException {
		
		String sql = "delete from email where idemail = " + idEmail + ";";

		ConnectionManager.getCurrentConnection().prepareStatement(sql).execute();

	}

	private Email montarEmail(ResultSet resultado) throws SQLException {
		Email email = new Email();

		email.setId(resultado.getLong("idemail"));
		email.setEmail(resultado.getString("email"));
		email.setContato(resultado.getLong("idcontato"));
		
		System.out.println("Email " + email.getEmail() + "foi montado");

		return email;
	}

}
