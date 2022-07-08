package br.com.tdec.avisosNocTDec.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import br.com.tdec.avisosNocTDec.model.Aviso;
import br.com.tdec.avisosNocTDec.model.AvisoNocTDec;
import br.com.tdec.avisosNocTDec.model.Chamado;
import br.com.tdec.avisosNocTDec.model.SendMessage;

@RestController
@RequestMapping(path = "/api/aviso")
public class AvisoController {
	
	@GetMapping
	public String aviso() {
		return "<H1> Huston...Aequi é Aviso </H1>";
	}

	@GetMapping(path = "/register")
	public String register() {
		 try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			 telegramBotsApi.registerBot(new AvisoNocTDec());
			 return "API Reiniciada em " + new Date();
			
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "<H1> Register... </H1>";
		
	}
	
	@GetMapping(path = "/send")
	public void send() {
		SendMessage.sendToTelegram("Hello World!", "-487538020");
		
	}
	
	@GetMapping(path = "/chamados")
	public String getChamados() {
		return Chamado.getChamados();
		
	}
	
	/**
	 * {
    "data" :"2012-04-23T18:25:43.511Z",
    "status" : "Pendente",
    "descricao" : "Notificação de Problema via Web - Demo Kikuda"
}
	 * @param aviso
	 */
	@PostMapping(path = "/mensagem")
	public void addAviso(@RequestBody Aviso aviso) {
		System.out.println("Iniciando post - aviso");
		SendMessage.sendToTelegram(aviso, "-487538020");
		
	
	}
	

}
