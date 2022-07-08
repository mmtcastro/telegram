package br.com.tdec.avisosNocTDec.view;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import br.com.tdec.avisosNocTDec.model.AvisoNocTDec;
import br.com.tdec.avisosNocTDec.model.Chamado;
import br.com.tdec.avisosNocTDec.model.SendMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Route("")
public class MainView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField mensagem = new TextField("Mensagem:");
	
	public MainView() {
		H2 h2 = new H2("Meu Telegram Controller");
		
		Button b1 = new Button("Register", event -> register());
		Button b2 = new Button("Messagem para Grupo com Admin", event -> sendMessage("-542293611"));
		Button b3 = new Button("Messagem para Grupo sem Admin", event -> sendMessage("-487538020"));
		Button b4 = new Button("GetChamados", event -> getChamados());
		HorizontalLayout hz = new HorizontalLayout(b1, b2, b3, b4);
		add(h2, mensagem,hz);
	}

	private void sendMessage(String chatId) {
		SendMessage.sendToTelegram(mensagem.getValue(), chatId);
	}

	private void register() {
		 try {
				TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
				 telegramBotsApi.registerBot(new AvisoNocTDec());
				
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void getChamados(){
		Chamado.getChamados();
	}

	

}
