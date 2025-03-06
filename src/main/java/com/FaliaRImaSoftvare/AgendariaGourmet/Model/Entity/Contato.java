package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Contato {
	private long id;
	private String nomeContato;
	private List<Email> emails;
	private List<NumeroTelefone> telefones;
	private long usuarioId;

	public Contato() {
	};

	public Contato(long id, String nomeContato, List<Email> emails, List<NumeroTelefone> telefones) {
		this.id = id;
		this.nomeContato = nomeContato;
		this.emails = emails;
		this.telefones = telefones;
	}

	public Contato(long usuarioId, String nomeContato) {
		this.usuarioId = usuarioId;
		this.nomeContato = nomeContato;
		this.emails = new ArrayList<Email>();
		this.telefones = new ArrayList<NumeroTelefone>();
	}

	public boolean addEmail(Email email) {
		if (email == null)
			return false;
		this.emails.add(email);

		return true;
	}

	public boolean addTelefone(NumeroTelefone telefones) {
		if (telefones == null)
			return false;
		this.telefones.add(telefones);

		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<NumeroTelefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<NumeroTelefone> telefones) {
		this.telefones = telefones;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
