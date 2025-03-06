package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.EmailService;

@Controller
@RequestMapping("{idUsuario}/{idContato}/email")
public class EmailController {

	@Autowired
	EmailService service;

	public EmailController() {
	}

	private String msg = null;

	@GetMapping("/criar")
	public String paginaCriar(@PathVariable Long idUsuario, @PathVariable Long idContato, Model m) {

		m.addAttribute("usuario", idUsuario);
		m.addAttribute("contato", idContato);
		m.addAttribute("url", "/usuario/homepage/"+idUsuario);
		m.addAttribute("msg", msg);

		return "paginas/criacao/TelaCadastroEmail";
	}

	@PostMapping("/criar")
	public String Criar(@PathVariable Long idUsuario, @PathVariable Long idContato, Model m, String email) {

		try {
			service.criar(idContato, email);

			return "redirect:/" + idUsuario + "/contato/consultar/" + idContato;

		} catch (Exception e) {
			msg = e.getMessage();
			return "redirect:/" + idUsuario + "/" + idContato + "/email/criar";

		}
	}

}
