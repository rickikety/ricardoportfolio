package com.ricardo.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ricardo.portfolio.email.Email;
import com.ricardo.portfolio.model.Mensagem;

@Controller
public class MainController {

	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String Main() {
		return "main";
	}
	
	@RequestMapping(value="/main", method=RequestMethod.POST)
	public String main(Mensagem mensagem, RedirectAttributes atributo) {
		
		if(mensagem.getAssunto().isEmpty() || mensagem.getEmail().isEmpty() || mensagem.getMensagem().isEmpty()) {
			atributo.addFlashAttribute("msgErro", "Favor preencher os campos obrigatórios");
			return "redirect:/main#ContatoI";
		}
		
		Email e = new Email();
		String AssEmail = "";
		AssEmail += "Portfolio/"+mensagem.getAssunto();
		String MsgEmail = "";
		MsgEmail += "Enviado: "+mensagem.getEmail()+"\n";
		if(!mensagem.getNome().isEmpty())
			MsgEmail += "Nome do solicitante: "+mensagem.getNome()+"\n";
		MsgEmail += mensagem.getMensagem();
		
		e.enviarEmail("ricardoengdepc@gmail.com", AssEmail, MsgEmail);
		atributo.addFlashAttribute("msgOk","Enviado com sucesso!");
		return "redirect:/main#ContatoI";
	}
	
}
