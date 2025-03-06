package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception;

public class CamposInvalidosException extends RuntimeException {

	
	public CamposInvalidosException(  ) {
	}
	public CamposInvalidosException(String mensagem ) {
		super(mensagem);
	}
}
