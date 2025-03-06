package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

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

@Controller
@RequestMapping("{userId}/contato")
public class ContatoController {

	private String msg = null;

	@Autowired
	private ContatoService service;

	public ContatoController() {
	}

	@GetMapping("/criar")
	public String paginaCriacao(Model m, @PathVariable Long userId) {

		m.addAttribute("usuario", userId);
		m.addAttribute("msg", msg);
		m.addAttribute("url", "/usuario/homepage/" + userId);
		msg = null;

		return "paginas/criacao/telaCriarContatos";
	}

	@PostMapping("/criar")
	public String criar(Model m, @PathVariable Long userId, ContatoDTO contato) {

		try {
			Long idContato = service.criar(contato, userId);

			return "redirect:/" + userId + "/contato/consultar/" + idContato;

		} catch (CamposInvalidosException e) {

			msg = e.getMessage();

			return "redirect:/{userId}/contato/criar/" + userId;

		}

	}

	@GetMapping("/consultar/{idContato}")
	public String consultar(Model m, @PathVariable Long idContato, @PathVariable Long userId) {


		try {
			Contato contato= service.consultar(userId, idContato);
			m.addAttribute("usuario",userId);
			m.addAttribute("contato", idContato);
			m.addAttribute("nomeContato", contato.getNomeContato());

			m.addAttribute("lEmail", contato.getEmails());
			m.addAttribute("lTelefoen", contato.getTelefones());
			m.addAttribute("url", "/usuario/homepage/"+userId);

			
			return "paginas/criacao/telaContato";

		} catch (Exception e) {
			msg = e.getMessage();
			m.addAttribute("msg", msg);
			System.out.println(e.getMessage());
			return "redirect:/usuario/homepage/" + userId;
		}

	}

	@GetMapping("/deletar/{idContato}")
	public String deletar(Model m, @PathVariable Long idContato, @PathVariable Long userId) {

		service.excluir(idContato);
		
		return "redirect:/usuario/homepage/" + userId;
	}

}
