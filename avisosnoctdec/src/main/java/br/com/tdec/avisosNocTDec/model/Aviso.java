package br.com.tdec.avisosNocTDec.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Aviso {
	private Date data;
	private String descricao;
	private String status;
	
	public Aviso(Date data, String descricao) {
		this.data = data;
		this.descricao = descricao;
	}
	
	public Aviso(String descricao) {
		this.data = new Date();
		this.descricao = descricao;
	}
	
	public String toMessage() {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		String strDate = dateFormat.format(data);
		return strDate + " - " + status + " - " + descricao;
	}

}
