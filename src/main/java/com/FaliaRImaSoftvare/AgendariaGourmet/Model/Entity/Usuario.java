package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;

public class Usuario {
	private long id;
	private String usuario;
	private String nome;
	private String senha;
	private String email;
	private List<Contato> contatos;

	public Usuario() {
		this.contatos = new ArrayList<Contato>();

	}

	public Usuario(String usuario, String nome, String senha, String email, List<Contato> contatos) {
		this.usuario = usuario;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.contatos = contatos;
	}

	public Usuario(String usuario, String nome, String senha, String email) {
		this.usuario = usuario;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.contatos = new ArrayList<Contato>();
	}

	public boolean addContato(Contato contatos) {
		if (contatos == null)
			return false;
		this.contatos.add(contatos);

		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.isBlank())
			throw new CamposInvalidosException("email não pode ser inserido por estar branco ou nulo");
		if (validarEmail(email) == false)
			throw new CamposInvalidosException("email não segue a formatação correta como no exemplo: Email@gmail.com");
		this.email = email;

	}
	
	public boolean validarEmail(String email) {
		if( email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) return true;
			return false;
		}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
}
