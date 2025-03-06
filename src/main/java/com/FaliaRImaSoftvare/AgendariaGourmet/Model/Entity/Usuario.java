package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

import java.util.ArrayList;
import java.util.List;

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
		this.email = email;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
}
