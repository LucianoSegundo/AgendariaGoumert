package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.ContatoDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Entity.Contato;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.ContatoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contato")
public class ContatoController {

	private String msg = null;

	@Autowired
	private ContatoService service;

	public ContatoController() {
	}

	@GetMapping("/criar")
	public String paginaCriacao(Model m, HttpSession session) {

		Long userId = (Long) session.getAttribute("usuario");

		if (userId == null)
			return "redirect:/usuario/login";

		m.addAttribute("msg", msg);
		m.addAttribute("url", "/usuario/homepage");
		msg = null;

		return "paginas/criacao/telaCriarContatos";
	}

	@PostMapping("/criar")
	public String criar(Model m, ContatoDTO contato, HttpSession session) {

		Long userId = (Long) session.getAttribute("usuario");

		try {

			Long idContato = service.criar(contato, userId);

			return "redirect:/contato/consultar/" + idContato;

		} catch (CamposInvalidosException e) {

			msg = e.getMessage();

			return "redirect:/contato/criar";

		}

	}

	@GetMapping("/consultar/{idContato}")
	public String consultar(Model m, @PathVariable Long idContato, HttpSession session) {

		Long userId = (Long) session.getAttribute("usuario");

		if (userId == null)
			return "redirect:/usuario/login";

		try {
			Contato contato = service.consultar(userId, idContato);
			m.addAttribute("usuario", userId);
			m.addAttribute("contato", idContato);
			m.addAttribute("nomeContato", contato.getNomeContato());

			m.addAttribute("lEmail", contato.getEmails());
			m.addAttribute("lTelefoen", contato.getTelefones());
			m.addAttribute("url", "/usuario/homepage");

			return "paginas/criacao/telaContato";

		} catch (Exception e) {
			msg = e.getMessage();
			m.addAttribute("msg", msg);
			System.out.println(e.getMessage());
			return "redirect:/usuario/homepage";
		}

	}

	@RequestMapping("/deletar/{idContato}")
	public String deletar(Model m, @PathVariable Long idContato, HttpSession session) {

		Long userId = (Long) session.getAttribute("usuario");

		service.excluir(idContato);
		String filto = (String) session.getAttribute("filtro");

		if (filto == null || filto.isBlank())
			return "redirect:/usuario/homepage";
		else
			return filtrarContatos(m, filto, session);

	}

	@PostMapping("/filtrar")
	public String filtrarContatos(Model m, String filtro, HttpSession session) {

		Long userId = (Long) session.getAttribute("usuario");

		if (userId == null)
			return "redirect:/usuario/login";

		try {
			List<Contato> lista = service.filtrar(userId, filtro);

			session.setAttribute("listaFiltrada", lista);
			session.setAttribute("filtro", filtro);

			return "redirect:/usuario/homepage";
		} catch (Exception e) {
			msg = e.getMessage();
			m.addAttribute("msg", msg);
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "redirect:/usuario/homepage";
		}

	}

}
