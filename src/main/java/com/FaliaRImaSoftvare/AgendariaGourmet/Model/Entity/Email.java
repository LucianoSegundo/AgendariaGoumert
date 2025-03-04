package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

public class Email {
    private long id;
    private String email;
    private long contato;

    public Email() {};
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
    	if(validarEmail(email) == false) return false;
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
    	
        return false;
    }
}

