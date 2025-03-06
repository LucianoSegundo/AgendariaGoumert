package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.ContatoDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Exception.CamposInvalidosException;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.ContatoService;

@Controller
@RequestMapping("{userId}/contato")
public class ContatoController {
	
	private String msg = null;
	
	@Autowired
	private ContatoService service;
	
	public ContatoController() {}
	
	@GetMapping("/criar")
	public String paginaCriacao(Model m, @PathVariable Long userId) {
		
		m.addAttribute("usuario", userId);
		m.addAttribute("msg", msg);
		m.addAttribute("url", "/usuario/homepage/"+ userId);
		msg = null;

		
		return "paginas/criacao/telaCriarContatos";
	}
	
	@PostMapping("/criar")
	public String criar(Model m, @PathVariable Long userId, ContatoDTO contato) {
		
		try {
			service.criar(contato, userId);
			
			return "redirect:/usuario/homepage/"+ userId;

		} catch (CamposInvalidosException e) {
			
			msg = e.getMessage();
			
			return "redirect:/{userId}/contato/criar/"+ userId;

		}
		
	}
	
	@GetMapping("/consultar/{id}")
	public String consultar(Model m) {
		
		return "paginas/criacao/telaContato";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletar(Model m) {
		
		return "paginas/criacao/telaCriarContatos";
	}

}
