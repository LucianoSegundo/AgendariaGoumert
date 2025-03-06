package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.ContatoDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.TelefoneDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Contato;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository.Fachada;

@Service
public class ContatoService {

	@Autowired
	EmailService emailService = new EmailService();
	
	@Autowired
	TelefoneService telefoneServi = new TelefoneService();

	Fachada fachada = Fachada.getCurrentInstance();
	
	public ContatoService() {}
	
	public Long criar(ContatoDTO con, Long userid) {
		Contato contato = new Contato();
		
		if(con.DDD() == null || con.DDD().isBlank()) throw new CamposInvalidosException("DDD branco ou nulo");
		if(con.Email() == null || con.Email().isBlank()) throw new CamposInvalidosException("Email branco ou nulo");
		if(con.nomeContato() == null || con.nomeContato().isBlank()) throw new CamposInvalidosException("Nome do contato branco ou nulo");
		if(con.telefone() == null || con.telefone().isBlank()) throw new CamposInvalidosException("numero de telefone branco ou nulo");
		if(userid == null) throw new CamposInvalidosException("Usuario invalido");
		
		contato.setNomeContato(con.nomeContato());;
		contato.setUsuarioId(userid);
		
		fachada.inserir(contato);
		
		contato = fachada.lerContato(con.nomeContato());
		
		emailService.criar(contato.getId(), con.Email());
		
		TelefoneDTO telefone = new TelefoneDTO(con.DDD(), con.telefone());
		
		telefoneServi.criar(contato.getId(),telefone );
		
		return contato.getId();
	
	}
	
	public void excluir(long id) {
		
		if(id == 0)  throw new CamposInvalidosException();
		
		fachada.deletarContato(id);
	}
	
	public void alterar(long id, String novoNome) {
		
		if(id == 0)  throw new CamposInvalidosException("id invalido");
		if(novoNome == null || novoNome.isBlank())  throw new CamposInvalidosException("novo nome branco ou nulo");
		
		Contato contato =  fachada.lerContato(id);
		contato.setNomeContato(novoNome);
		
		fachada.alterar(contato); 
	}

	public Contato consultar(Long userId, Long id) throws Exception {
		if(id == null)  throw new CamposInvalidosException("id invalido");


		Contato contato = fachada.lerContato(id);
		
		if(contato.getUsuarioId() != userId) throw new AcessoIlegalException("O contato não pertence ao usuário");
		
		return contato;
	}

}
