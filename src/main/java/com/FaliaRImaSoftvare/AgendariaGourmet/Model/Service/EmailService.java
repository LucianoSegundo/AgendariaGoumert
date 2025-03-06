package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service;

import org.springframework.stereotype.Service;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Email;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository.EmailRepository;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository.Fachada;

@Service
public class EmailService {

	Fachada fachada = Fachada.getCurrentInstance();

	public EmailService() {
	}

	public void criar(Long idContato, String enderecoEmail) {
		if (idContato == null)
			throw new CamposInvalidosException("insersão não permitida, Id do contato invalido");

		Email email = new Email();

		if (email.setEmail(enderecoEmail) == true)
			email.setContato(idContato);
		fachada.inserir(email);

	}

	public void alterar(Long idEmail, String novoEmail) {
		if (idEmail == null)
			throw new CamposInvalidosException("alteração não permitida, Id do email invalido");

		fachada.lerEmail(idEmail);
		Email email = fachada.lerEmail(idEmail);

		if (email.setEmail(novoEmail) == true)
			fachada.alterar(email);

	}

	public void excluir(Long idEmail) {
		if (idEmail == null)
			throw new CamposInvalidosException("deleção não permitida, Id do email invalido");

		fachada.deletarEmail(idEmail);

	}
}
