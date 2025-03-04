package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.NumeroTelefone;

public class TelefoneRepository implements Repositorio<NumeroTelefone, Long> {

	@Override
	public void inserir(NumeroTelefone telefone) throws SQLException {
		String query = "insert into numerotelefone (numeroDDD,numeroTelefone, idcontato)" + "values(?,?,?)" + ";";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, telefone.getNumeroDDD());
		pstm.setString(2, telefone.getNumeroTelefone());
		pstm.setLong(3, telefone.getContato());

		pstm.execute();
		
	}

	@Override
	public void alterar(NumeroTelefone telefone) throws SQLException {
		String query = "update numerotelefone set numeroDDD=?, numeroTelefone=? where idtelefone =" + telefone.getId() + ";";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(query);

		pstm.setString(1, telefone.getNumeroDDD());
		pstm.setString(1, telefone.getNumeroTelefone());


		pstm.execute();
		
	}

	@Override
	public NumeroTelefone ler(Long idtelefone) throws SQLException {
		String query = "select * from numerotelefone where idtelefone = " + idtelefone + ";";

		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();

		NumeroTelefone telefone = new NumeroTelefone();
		if (resultado.next()) {

			telefone = montartelefone(resultado);

		}
		return telefone;
	}
	
	public List<NumeroTelefone> listar(Long idcontato) throws SQLException {
				
			
		String query = "select * from numerotelefone where idcontato = " + idcontato + ";";

		ResultSet resultado = ConnectionManager.getCurrentConnection().prepareStatement(query).executeQuery();

		NumeroTelefone telefone = new NumeroTelefone();
		
		List<NumeroTelefone> listaTelefone = new ArrayList<NumeroTelefone>();

		while (resultado.next()) {

			telefone = montartelefone(resultado);

			listaTelefone.add(telefone);
		}
		return listaTelefone;
		
	}


	@Override
	public void delete(Long idtelefone) throws SQLException {
		String sql = "delete from numerotelefone where idtelefone = " + idtelefone + ";";

		ConnectionManager.getCurrentConnection().prepareStatement(sql).execute();
		
	}
	private NumeroTelefone montartelefone(ResultSet resultado) throws SQLException {
		NumeroTelefone telefone = new NumeroTelefone();

		telefone.setId(resultado.getLong("idtelefone"));
		telefone.setNumeroDDD(resultado.getString("numeroDDD"));
		telefone.setNumeroTelefone(resultado.getString("numeroTelefone"));
		telefone.setContato(resultado.getLong("idcontato"));
		
		System.out.println("Telefone " + telefone.getNumeroTelefone() + "foi montado");

		return telefone;
	}

	@Override
	public NumeroTelefone ler(String k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
