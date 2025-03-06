package com.FaliaRImaSoftvare.AgendariaGourmet.Model.Repository;

import java.sql.SQLException;
import java.util.List;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Contato;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Email;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.NumeroTelefone;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Usuario;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.ErroDeInsercaoException;

public class Fachada {

	private static Fachada myself = null;

	private Repositorio<Usuario, Long> userRepo;
	private ContatoRepository contaRepo;
	private Repositorio<Email, Long> emailRepo;
	private Repositorio<NumeroTelefone, Long> teleRepo;

	public Fachada() {

		this.contaRepo = new ContatoRepository();
		this.userRepo = new UsuarioRepository();
		this.emailRepo = new EmailRepository();
		this.teleRepo = new TelefoneRepository();
	}

	public static Fachada getCurrentInstance() {

		if (myself == null) {
			myself = new Fachada();
		}

		return myself;
	}

// Inserir

	// Usuario

	public void inserir(Usuario user) {
		try {
			userRepo.inserir(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel inserir o usuario");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// Contato

	public void inserir(Contato contato) {
		try {
			contaRepo.inserir(contato);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel inserir o contato");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// Email

	public void inserir(Email email) {
		try {
			emailRepo.inserir(email);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel inserir o Email");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// telefone

	public void inserir(NumeroTelefone telfone) {
		try {
			teleRepo.inserir(telfone);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel inserir o email");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

// alterar

	// usuario

	public void alterar(Usuario usuario) {
		try {
			userRepo.alterar(usuario);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel alterar o usuario");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// contato

	public void alterar(Contato contato) {
		try {
			contaRepo.alterar(contato);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel alterar o contato");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// email

	public void alterar(Email email) {
		try {
			emailRepo.alterar(email);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel alterar o email");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// telefone

	public void alterar(NumeroTelefone telfone) {
		try {
			teleRepo.alterar(telfone);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel alterar o telefone");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

// deletar

	// usuario

	public void deletarUdsuario(long id) {
		try {
			userRepo.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel deletar o usuario");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// contato

	public void deletarContato(long id) {
		try {
			contaRepo.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel deletar o contato");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// Email

	public void deletarEmail(long id) {
		try {
			emailRepo.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel deletar o email");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// telefone

	public void deletarTelefone(long id) {
		try {
			teleRepo.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel deletar o telefone");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

// ler

	// usuario

	public Usuario lerUsuario(long id) {
		try {

			return userRepo.ler(id);

		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel ler o usuario pelo id");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	public Usuario lerUsuario(String usuario) {
		try {
			return userRepo.ler(usuario);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel ler o usuario pelo nome");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// contato

	public Contato lerContato(String nome) {
		try {
			return contaRepo.ler(nome);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException(
					"não foi possivel ler o contato pelo nome do contato");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	public Contato lerContato(long id) {
		try {
			return contaRepo.ler(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel ler o telefone pelo id");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	public List<Contato> listarContato(long idUsuario) {
		try {
			return contaRepo.listar(idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel ler o telefone pelo id");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// email

	public Email lerEmail(long id) {
		try {
			return emailRepo.ler(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel ler o email pelo id");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	public List<Email> listarEmail(long idContato) {
		try {
			return emailRepo.listar(idContato);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException(
					"não foi possivel ler o telefone pelo id do contato");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	// telefone

	public NumeroTelefone lerTelefone(long id) {
		try {
			return teleRepo.ler(id);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel ler o telefone pelo id");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	public List<NumeroTelefone> listarTelefone(long idContato) {
		try {
			return teleRepo.listar(idContato);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException(
					"não foi possivel ler o telefone pelo id do contato");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

	public List<Contato> filtrar(Long userId, String filtro) {
		try {
			return contaRepo.filtrar(userId, filtro);
		} catch (SQLException e) {
			e.printStackTrace();

			ErroDeInsercaoException erro = new ErroDeInsercaoException("não foi possivel filtrar");

			erro.setStackTrace(e.getStackTrace());
			throw erro;
		}
	}

}
