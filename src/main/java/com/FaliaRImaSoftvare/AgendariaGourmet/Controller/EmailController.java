package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.TelefoneDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/{idContato}/email")
public class EmailController {

	@Autowired
	EmailService service;

	public EmailController() {
	}

	private String msg = null;

	@GetMapping("/criar")
	public String paginaCriar(@PathVariable Long idContato, Model m, HttpSession session) {

		Long id = (Long) session.getAttribute("usuario");

		if (id == null)
			return "redirect:/usuario/login";

		m.addAttribute("contato", idContato);
		m.addAttribute("url", "/contato/consultar/" + idContato);
		m.addAttribute("msg", msg);

		return "paginas/criacao/TelaCadastroEmail";
	}

	@PostMapping("/criar")
	public String Criar(@PathVariable Long idContato, Model m, String email) {

		try {
			service.criar(idContato, email);

			return "redirect:/contato/consultar/" + idContato;

		} catch (Exception e) {
			msg = e.getMessage();
			return "redirect:/" + idContato + "/email/criar";

		}
	}

	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable Long idContato, @PathVariable Long id, Model m, TelefoneDTO telefone) {

		try {
			service.excluir(id);

			return "redirect:/contato/consultar/" + idContato;

		} catch (Exception e) {
			msg = e.getMessage();
			return "redirect:/contato/consultar/" + idContato;

		}
	}

}
