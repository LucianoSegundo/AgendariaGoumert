package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.sql.SQLException;

public interface Repositorio <CLAZZ,KEY> {
	public void inserir(CLAZZ z) throws SQLException;
	public void alterar(CLAZZ z) throws SQLException;
	public CLAZZ ler(KEY k) throws SQLException;
	public void delete(KEY k) throws SQLException;
	
}
