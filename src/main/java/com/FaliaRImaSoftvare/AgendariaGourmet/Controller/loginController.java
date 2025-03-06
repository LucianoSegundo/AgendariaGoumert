package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.LoginDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.usuarioDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Usuario;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.AcessoNegadoException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.EntidadeNaoEncontrada;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.RecursoJaExisteException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class loginController {
	private String msg = null;
	private Usuario user = null;
	
	@Autowired
	private UsuarioService service;
	
	public loginController() {}

	@GetMapping("/cadastro")
	public String redirecionar(Model m) {

		m.addAttribute("msg", null);
		m.addAttribute("msg", msg);
		msg = null;

		return "paginas/criacao/telaCadastroUsuario";
	}

	@GetMapping("/login")
	public String redirecionarLogin(Model m) {

		m.addAttribute("msg", null);
		m.addAttribute("msg", msg);
		msg = null;

		return "index";
	}

	@PostMapping("/login")
	public String logar(Model m, LoginDTO usuario) {

		try {
			user = service.logar(usuario);

			return "redirect:/usuario/homepage/" + user.getId();

		} catch (RecursoJaExisteException e) {

			msg = "Usuario já cadastrado";
		} catch (CamposInvalidosException e) {
			msg = "Login negado devido a campos invaliddos";
		} catch (EntidadeNaoEncontrada e) {
			msg = "Usuario não encontrado";
		} catch (AcessoNegadoException e) {

			msg = "Acesso negado";
		}

		return "redirect:/usuario/login";

	}

	@PostMapping("/cadastro")
	public String cadastrar(Model m, usuarioDTO usuario) {

		try {
			user = service.cadastrarUsuario(usuario);

			return "redirect:/usuario/homepage/" + user.getId();

		} catch (RecursoJaExisteException e) {
			msg = "Usuario já cadastrado";
		} catch (CamposInvalidosException e) {
			msg = "cadastro negado devido a campos invaliddos";
		}

		return "redirect:/usuario/cadastro";

	}

	@GetMapping("/homepage/{id}")
	public String homepage(Model m, @PathVariable long id) {

		user = service.consultarUsuario(id);
		m.addAttribute("contatos", user.getContatos());
		m.addAttribute("usuario", id);

		return "paginas/homePage";
	}

}
