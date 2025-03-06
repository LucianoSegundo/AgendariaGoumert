package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service;

import org.springframework.stereotype.Service;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.TelefoneDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.NumeroTelefone;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository.Fachada;

@Service
public class TelefoneService {

	Fachada fachada = Fachada.getCurrentInstance();

	public TelefoneService() {
	}

	public void criar(Long id, TelefoneDTO telefone) {
		if (id == null)
			throw new CamposInvalidosException("Id do contato invalido, não foi possivel criar contato");

		NumeroTelefone fone = new NumeroTelefone();

		fone.setContato(id);

		fone.setNumeroDDD(telefone.ddd());

		fone.setNumeroTelefone(telefone.telefone());

		fachada.inserir(fone);

	}

	public void alterar(Long idtelefone, TelefoneDTO telefone) {
		if (idtelefone == null)
			throw new CamposInvalidosException("Id do telefone invalido, não foi possivel alterar o telefone");

		NumeroTelefone fone = fachada.lerTelefone(idtelefone);

		if (telefone.ddd() != null)
			fone.setNumeroDDD(telefone.ddd());

		if (telefone.telefone() != null)
			fone.setNumeroTelefone(telefone.telefone());

		if (telefone.ddd() != null || telefone.telefone() != null)
			fachada.inserir(fone);

	}

	public void excluir(Long id) {
		if (id == null)
			throw new CamposInvalidosException("Id do telefone invalido, não foi possivel excluir telefone");

		fachada.deletarTelefone(id);

	}
}
