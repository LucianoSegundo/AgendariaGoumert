package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service;

import org.springframework.stereotype.Service;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.LoginDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.usuarioDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Usuario;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.AcessoNegadoException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.EntidadeNaoEncontrada;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.RecursoJaExisteException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository.Fachada;

@Service
public class UsuarioService {

	public UsuarioService() {};
	
	Fachada fachada = Fachada.getCurrentInstance();
	public Usuario cadastrarUsuario(usuarioDTO request) {
		
		if(request.senha() == null ||request.senha().isBlank() ||
				request.usuario()== null || request.usuario().isBlank()) throw new CamposInvalidosException();
		
		if(request.email() == null ||request.email().isBlank() ||
				request.nome()== null || request.nome().isBlank()) throw new CamposInvalidosException();
		
		Usuario teste = fachada.lerUsuario(request.usuario());
		
		if(teste != null) throw new RecursoJaExisteException();
		
		Usuario user = new Usuario(request.usuario(), request.nome(), request.senha(), request.email());
		String senha = Integer.toString(request.senha().hashCode());
		
		user.setSenha(senha);
		
		fachada.inserir(user);
		
		user = fachada.lerUsuario(request.nome());
		
		return user;
				
		
	}
	
	public Usuario logar(LoginDTO request) {
		
		if(request.senha() == null ||request.senha().isBlank() ||
				request.usuario()== null || request.usuario().isBlank()) throw new CamposInvalidosException();
		
		Usuario user = fachada.lerUsuario(request.usuario());
		
		if(user == null) throw new EntidadeNaoEncontrada();
		
		String senha = Integer.toString(request.senha().hashCode());
		
		if(user.getSenha().equals(senha) != true) throw new AcessoNegadoException();
		
		return user;
	}

	public Usuario consultarUsuario(long id) {
		
		Usuario user = fachada.lerUsuario(id);

		return user;
	}
}
