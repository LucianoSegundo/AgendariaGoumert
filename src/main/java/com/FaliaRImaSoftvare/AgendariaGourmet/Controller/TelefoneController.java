package com.FaliaRImaSoftvare.AgendariaGourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FaliaRImaSoftvare.AgendariaGourmet.Controller.Dto.TelefoneDTO;
import com.FaliaRImaSoftvare.AgendariaGourmet.Model.Service.TelefoneService;

@Controller
@RequestMapping("{idUsuario}/{idContato}/telefone")
public class TelefoneController {

	@Autowired
	TelefoneService service;
	
	private String msg = null;
	
	public TelefoneController(){}
	

	@GetMapping("/criar")
	public String paginaCriar(@PathVariable Long idUsuario, @PathVariable Long idContato, Model m) {

		m.addAttribute("usuario", idUsuario);
		m.addAttribute("contato", idContato);
		m.addAttribute("url", "/" + idUsuario + "/contato/consultar/" + idContato);
		m.addAttribute("msg", msg);
		

		return "paginas/criacao/TelaCadastroNumero";
	}

	@PostMapping("/criar")
	public String Criar(@PathVariable Long idUsuario, @PathVariable Long idContato, Model m, TelefoneDTO telefone) {

		try {
			service.criar(idContato, telefone);

			return "redirect:/" + idUsuario + "/contato/consultar/" + idContato;

		} catch (Exception e) {
			msg = e.getMessage();
			return "redirect:/" + idUsuario + "/" + idContato + "/telefone/criar";

		}
	}
	
	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable Long idUsuario, @PathVariable Long idContato, @PathVariable Long id, Model m, TelefoneDTO telefone) {

		try {
			service.excluir(id);

			return "redirect:/" + idUsuario + "/contato/consultar/" + idContato;

		} catch (Exception e) {
			msg = e.getMessage();
			return "redirect:/" + idUsuario + "/contato/consultar/" + idContato;

		}
	}
}
