package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity;

public class Email {
    private long id;
    private String email;

    public Email(long id, String email) {
        this.id = id;
        this.email = email;
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

    public boolean validarEmail(String email) {
    	
        return false;
    }
}

