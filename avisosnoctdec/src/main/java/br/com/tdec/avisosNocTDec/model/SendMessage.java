package br.com.tdec.avisosNocTDec.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import lombok.Data;

@Data
public class SendMessage {
//	private String chatId;
//	private String urlString;
//	private String apiToken;
//	private String message;

	public static void sendToTelegram(String message, String chatId) {
		String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
		
		String apiToken = "1657233577:AAGjJ0FMyikccXRodnHQNSjxWPjqa0ttwvg";
		
		//String chatId = "-542293611";
       // String text = "Hello world!";
        
        urlString = String.format(urlString, apiToken, chatId, message);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public static void sendToTelegram(Aviso aviso, String chatId) {
		sendToTelegram(aviso.toMessage(), chatId);
	}
}
