package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;

public class Email {
	private long id;
	private String email;
	private long contato;

	public Email() {
	};

	public Email(long id, String email, long contato) {
		this.id = id;
		this.email = email;
		this.setContato(contato);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if (email == null || email.isBlank())
			throw new CamposInvalidosException("email não pode ser inserido por estar branco ou nulo");
		if (validarEmail(email) == false)
			throw new CamposInvalidosException("email não segue a formatação correta");
		this.email = email;

		return true;
	}

	public long getContato() {
		return contato;
	}

	public void setContato(long contato) {
		this.contato = contato;
	}

	public boolean validarEmail(String email) {

		return true;
	}
}
