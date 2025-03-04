package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

public class NumeroTelefone {
    private long id;
    private String numeroDDD;
    private String numeroTelefone;
    private long contato;

    public NumeroTelefone() {};
    
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
    	if(validarNumeroDDD(numeroDDD)== false) return false;
        this.numeroDDD = numeroDDD;
        
        return true;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public boolean setNumeroTelefone(String numeroTelefone) {
    	if(validarNumeroTelefone(numeroTelefone) == false) return false;
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

    	return numeroTelefone != null && numeroTelefone.matches("\\d{10,11}");
    }

    public boolean validarNumeroDDD(String numeroDDD) {
        return numeroDDD != null && numeroDDD.matches("\\d{2}");
    }
}
