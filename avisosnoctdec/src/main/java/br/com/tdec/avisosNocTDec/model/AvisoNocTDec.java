package br.com.tdec.avisosNocTDec.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AvisoNocTDec extends TelegramLongPollingBot {

	private static final Logger logger = LoggerFactory.getLogger(AvisoNocTDec.class);
//	@Value("${bot.token}")
//	private String token;
//
//	@Value("${bot.username}")
//	private String username;

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "mmtcastro_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1657233577:AAGjJ0FMyikccXRodnHQNSjxWPjqa0ttwvg";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			Message message = update.getMessage();
			SendMessage response = new SendMessage();
			SendMessage newResponse = new SendMessage();
			Long chatId = message.getChatId();
			response.setChatId(String.valueOf(chatId));
			String text = "";
			// comandos = com funcionam
			String command = update.getMessage().getText();
			System.out.println("Command eh " + command);
			if (command.equals("/chamados")) {
				System.out.println("Selecionou Chamados");
				text = "Você tem 20 chamados abertos";
			} else if(command.equals("/chamados@mmtcastro_bot")) {
				text = "Você tem 30 chamados abertos - vindo do Grupo Avisos";
			}else if(command.equals("/contratos")) {
				text = "Nenhum Contrato Vencido";
			}else if(command.equals("/contratos@mmtcastro_bot")) {
				text = "Nenhum Contrato Vencido - vindo do Grupo Avisos";
			}  else {
				text = "Olá, " + update.getMessage().getFrom().getFirstName() + " " + //
						update.getMessage().getFrom().getLastName() + " , tudo bem?";
			}
			response.setText(text);
			

			try {
				execute(response);
				response.setText("Em que posso ajudar?");
				execute(response);	
				newResponse.setText("Este é o new response");
				execute(newResponse);
				logger.info("Sent message \"{}\" to {}", text, chatId);
			} catch (TelegramApiException e) {
				logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
			}
		}
	}
	
	

//	@PostConstruct
//	public void start() {
//		logger.info("username: {}, token: {}", username, token);
//	}
	

}
