package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private final static String diretorio = "target/banco.db";
	
	private static final String URL = "jdbc:sqlite:" + diretorio;
	private static final String usuario = "root";
	private static final String senha = "";
	
	private static Connection conn = null;

	
	public static Connection getCurrentConnection() throws SQLException{
		File file = new File(diretorio);
		
		if(conn == null && file.exists() == true) {
			conn = conteudo( conn);
		} 
		else if(conn == null && file.exists()== false) {
			conn = conteudo( conn);
			
			criarBanco(conn);
		}
	
		
		return conn;
	}
	
	static Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection(URL, usuario, senha);
	}
	
	private static Connection conteudo(Connection conn) {
		try {
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL, usuario, senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	private static void criarBanco(Connection conn) throws SQLException {
		// criar tabela de usuarios;
		
		String query= "CREATE TABLE Usuario (\n"
				+ "    idusuario INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "    usuario TEXT NOT NULL,\n"
				+ "    nome TEXT NOT NULL,\n"
				+ "    senha TEXT NOT NULL,\n"
				+ "    email TEXT NOT NULL\n"
				+ ");";
		
		
		System.out.println( "Print ineficas que serviria pra atestar a criação da tabela, mas que na verdade não faz isso" + conn.prepareStatement(query).execute());
		
		System.out.println(query);
		
		// criar tabela de contatos;
		
		query = "CREATE TABLE Contato (\n"
				+ "    idcontato INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "    nomeContato TEXT NOT NULL,\n"
				+ "    idusuario INTEGER,\n"
				+ "    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario) ON DELETE CASCADE\n"
				+ ");\n";
		
		System.out.println( "Print ineficas que serviria pra atestar a criação da tabela, mas que na verdade não faz isso" + conn.prepareStatement(query).execute());

		System.out.println(query);

		// criar tabela de email
		
		query = "CREATE TABLE Email (\n"
				+ "    idemail INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "    email TEXT NOT NULL,\n"
				+ "    idcontato INTEGER,\n"
				+ "    FOREIGN KEY (idcontato) REFERENCES Contato(idcontato) ON DELETE CASCADE\n"
				+ ");";
		
		
		System.out.println( "Print ineficas que serviria pra atestar a criação da tabela, mas que na verdade não faz isso" + conn.prepareStatement(query).execute());
		
		System.out.println(query);

		// criar tabela contatos 
		
		query = "CREATE TABLE NumeroTelefone (\n"
				+ "    idtelefone INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "    numeroDDD TEXT NOT NULL,\n"
				+ "    numeroTelefone TEXT NOT NULL,\n"
				+ "    idcontato INTEGER,\n"
				+ "    FOREIGN KEY (idcontato) REFERENCES Contato(idcontato) ON DELETE CASCADE\n"
				+ ");";
		
		System.out.println( "Print ineficas que serviria pra atestar a criação da tabela, mas que na verdade não faz isso" + conn.prepareStatement(query).execute() );
		
		System.out.println(query);
			
	}
	
}
