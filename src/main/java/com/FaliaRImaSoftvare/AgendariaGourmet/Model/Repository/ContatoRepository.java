package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Contato;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Email;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.NumeroTelefone;

public class ContatoRepository implements Repositorio<Contato, Long> {

	private EmailRepository emailRepo = new EmailRepository();
	private TelefoneRepository telefoneRepo = new TelefoneRepository();

	@Override
	public void inserir(Contato contato) throws SQLException {
		String query = "insert into Contato (nomeContato, idusuario)" + "values(?,?)" + ";";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, contato.getNomeContato());
		pstm.setLong(2, contato.getUsuarioId());

		pstm.execute();

	}

	@Override
	public void alterar(Contato contato) throws SQLException {

		String query = "update Contato set nomeContato=? where idcontato =" + contato.getId() + ";";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, contato.getNomeContato());

		pstm.execute();

	}

	@Override
	public Contato ler(Long idcontato) throws SQLException {
		String query = "select * from Contato where idcontato = " + idcontato + ";";

		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();

		Contato contato = new Contato();
		if (resultado.next()) {

			contato = montarContato(resultado);

		}
		return contato;
	}

	public Contato ler(String nomeContato) throws SQLException {
		String query = "select * from Contato where nomecontato = ?;";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);
		pstm.setString(1, nomeContato);

		ResultSet resultado = pstm.executeQuery();

		Contato contato = new Contato();
		if (resultado.next()) {

			contato = montarContato(resultado);

		}
		return contato;
	}

	public List<Contato> listar(Long idusuario) throws SQLException {

		String query = "select * from Contato where idusuario = ?;";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setLong(1, idusuario);

		ResultSet resultado = pstm.executeQuery();

		Contato contato = new Contato();
		List<Contato> listaContato = new ArrayList<Contato>();

		while (resultado.next()) {

			contato = montarContato(resultado);

			listaContato.add(contato);
		}

		return listaContato;
	}

	@Override
	public void delete(Long idcontato) throws SQLException {

		String query = "delete from Contato where idcontato = ?;";

		List<NumeroTelefone> telefones = telefoneRepo.listar(idcontato);

		for (NumeroTelefone telefone : telefones) {
			telefoneRepo.delete(telefone.getId());

		}
		;

		List<Email> emails = emailRepo.listar(idcontato);

		for (Email email : emails) {
			emailRepo.delete(email.getId());

		}
		;
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setLong(1, idcontato);
		pstm.execute();

	}

	private Contato montarContato(ResultSet resultado) throws SQLException {

		Contato contato = new Contato();

		contato.setId(resultado.getLong("idcontato"));
		contato.setNomeContato(resultado.getString("nomeContato"));
		contato.setUsuarioId(resultado.getLong("idusuario"));

		List<NumeroTelefone> telefones = telefoneRepo.listar(contato.getId());
		contato.setTelefones(telefones);

		List<Email> emails = emailRepo.listar(contato.getId());
		contato.setEmails(emails);

		return contato;
	}

	public List<Contato> filtrar(Long userId, String filtro) throws SQLException {

		String query = "select * from Contato where idusuario = ?" + "AND nomeContato LIKE ? "
				+ "AND nomeContato NOT LIKE ? " + "AND nomeContato NOT LIKE ?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setLong(1, userId);
		pstm.setString(2, "%" + filtro + "%");
		pstm.setString(3, "% " + filtro + "%");
		pstm.setString(4, "% " + filtro + "");

		ResultSet resultado = pstm.executeQuery();

		Contato contato = new Contato();
		List<Contato> listaContato = new ArrayList<Contato>();

		while (resultado.next()) {

			contato = montarContato(resultado);

			listaContato.add(contato);
		}

		return listaContato;
	}

}
