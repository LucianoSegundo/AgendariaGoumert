package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.LoginDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.usuarioDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Contato;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Usuario;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.AcessoNegadoException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.EntidadeNaoEncontrada;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.RecursoJaExisteException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class loginController {
	private String msg = null;
	private Usuario user = null;

	@Autowired
	private UsuarioService service;

	@Autowired
	private HttpSession session;

	public loginController() {
	}

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

			session.setAttribute("usuario", user.getId());
			session.setAttribute("usuarioNome", user.getNome());
			return "redirect:/usuario/homepage";

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
			Usuario iduser = service.cadastrarUsuario(usuario);

			session.setAttribute("usuario", iduser.getNome());
			session.setAttribute("usuarioNome", user.getNome());

			return "redirect:/usuario/homepage";

		} catch (RecursoJaExisteException e) {
			msg = "Usuario já cadastrado";
		} catch (CamposInvalidosException e) {
			msg = "cadastro negado devido a campos invaliddos";
		}

		return "redirect:/usuario/cadastro";

	}

	@GetMapping("/homepage")
	public String homepage(Model m, HttpSession session) {

		Long id = (Long) session.getAttribute("usuario");

		if (id == null)
			return "redirect:/usuario/login";

		List<Contato> lista = (List<Contato>) session.getAttribute("listaFiltrada");

		List<Contato> listaInserida;

		if (lista != null && lista.size() > 0)
			listaInserida = lista;
		else {
			user = service.consultarUsuario(id);
			listaInserida = user.getContatos();
			session.removeAttribute("filtro");
		}

		m.addAttribute("contatos", listaInserida);

		return "paginas/homePage";
	}

	@GetMapping("deslogar")
	public String deslogar(Model m, HttpSession session) {

		session.invalidate();

		return "redirect:/usuario/login";
	}

	@GetMapping("/perfil")
	public String perfil(Model m, HttpSession session) {

		Long id = (Long) session.getAttribute("usuario");

		if (id == null)
			return "redirect:/usuario/login";
		Usuario user = service.consultarUsuario(id);

		m.addAttribute("user", user);
		m.addAttribute("msg", null);
		m.addAttribute("msg", msg);
		msg = null;

		return "paginas/criacao/TelaPerfil";
	}

	@PostMapping("/perfil")
	public String alterarPerfil(Model m, HttpSession session, usuarioDTO dto) {

		Long id = (Long) session.getAttribute("usuario");

		if (id == null)
			return "redirect:/usuario/login";

		Usuario user = service.alterar(id, dto);
		session.setAttribute("usuarioNome", user.getNome());

		msg = null;

		return "redirect:/usuario/homepage";
	}

	@GetMapping("/limparfiltro")
	public String limparfiltro(Model m) {
		session.removeAttribute("listaFiltrada");
		session.removeAttribute("filtro");

		return "redirect:/usuario/homepage";
	}
}
