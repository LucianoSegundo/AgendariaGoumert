package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;

public class NumeroTelefone {
	private long id;
	private String numeroDDD;
	private String numeroTelefone;
	private long contato;

	public NumeroTelefone() {
	};

	public NumeroTelefone(String numeroDDD, String numeroTelefone, long contato) {
		this.numeroDDD = numeroDDD;
		this.numeroTelefone = numeroTelefone;
		this.setContato(contato);
	};

	public NumeroTelefone(long id, String numeroDDD, String numeroTelefone, long contato) {
		this.id = id;
		this.numeroDDD = numeroDDD;
		this.numeroTelefone = numeroTelefone;
		this.setContato(contato);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumeroDDD() {
		return numeroDDD;
	}

	public boolean setNumeroDDD(String numeroDDD) {
		if (numeroDDD == null || numeroDDD.isBlank())
			throw new CamposInvalidosException("não foi possivel inserir o numero do DDD, campo invalido");
		if (validarNumeroDDD(numeroDDD) == false)
			throw new CamposInvalidosException("DDD não segue o formato correto, como no exemplo: 81");
		this.numeroDDD = numeroDDD;

		return true;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public boolean setNumeroTelefone(String numeroTelefone) {
		if (numeroDDD == null || numeroDDD.isBlank())
			throw new CamposInvalidosException("não foi possivel inserir o numero do Telefone, campo invalido");
		if (validarNumeroTelefone(numeroTelefone) == false)
			throw new CamposInvalidosException("Telefone não segue o formato correto, como no exemplo: 98888-8888");
		this.numeroTelefone = numeroTelefone;

		return true;
	}

	public long getContato() {
		return contato;
	}

	public void setContato(long contato) {
		this.contato = contato;
	}

	public boolean validarNumeroTelefone(String numeroTelefone) {
	if(numeroTelefone.matches("^9\\d{4}-\\d{4}$")) return true;
		
		return false;
	}

	public boolean validarNumeroDDD(String numeroDDD) {
		
		if(numeroDDD.matches("^[1-9]{2}$")) return true;
		
		return false;
	}
}
