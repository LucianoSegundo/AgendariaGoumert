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

	public UsuarioService() {
	};

	Fachada fachada = Fachada.getCurrentInstance();

	public Usuario cadastrarUsuario(usuarioDTO request) {

		if ( request.usuario() == null || request.usuario().isBlank())
			throw new CamposInvalidosException("Usuario blanco ou nulo");
		if (request.senha() == null || request.senha().isBlank())
			throw new CamposInvalidosException("Senha blanca ou nula");
		
		if ( request.nome() == null || request.nome().isBlank())
			throw new CamposInvalidosException("Nome blanco ou nulo");
		
		if (request.email() == null || request.email().isBlank() )
			throw new CamposInvalidosException("Email blanco ou nulo");

		Usuario teste = fachada.lerUsuario(request.usuario());

		if (teste != null)
			throw new RecursoJaExisteException();

		Usuario user = new Usuario(request.usuario(), request.nome(), request.senha(), request.email());
		String senha = Integer.toString(request.senha().hashCode());

		user.setSenha(senha);
		user.setEmail(request.email());

		fachada.inserir(user);

		user = fachada.lerUsuario(request.usuario());

		return user;

	}

	public Usuario logar(LoginDTO request) {

		if (request.senha() == null || request.senha().isBlank() || request.usuario() == null
				|| request.usuario().isBlank())
			throw new CamposInvalidosException();

		Usuario user = fachada.lerUsuario(request.usuario());

		if (user == null)
			throw new EntidadeNaoEncontrada();

		String senha = Integer.toString(request.senha().hashCode());

		if (user.getSenha().equals(senha) != true)
			throw new AcessoNegadoException();

		return user;
	}

	public Usuario consultarUsuario(long id) {

		Usuario user = fachada.lerUsuario(id);

		return user;
	}

	public Usuario alterar(long id, usuarioDTO dto) {

		Usuario user = fachada.lerUsuario(id);
		boolean alterado = false;

		if (dto.nome() != null && dto.nome().isBlank() == false) {
			user.setNome(dto.nome());
			alterado = true;
		}
		if (dto.email() != null && dto.email().isBlank() == false) {
			user.setEmail(dto.email());
			alterado = true;

		}

		if (dto.senha() != null && dto.senha().isBlank() == false) {

			String senha = Integer.toString(dto.senha().hashCode());

			user.setSenha(senha);
			alterado = true;

		}
		;

		if (alterado = true) {
			fachada.alterar(user);
		}

		return user;
	}
}
