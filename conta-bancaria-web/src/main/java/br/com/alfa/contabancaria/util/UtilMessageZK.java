package br.com.alfa.contabancaria.util;

import org.zkoss.zul.Messagebox;

public final class UtilMessageZK {
	
	//Utility class
	private UtilMessageZK() {
		
	}
	
	public static void showMessageInfo(String mensagem) {
		Messagebox.show(mensagem, null, 0, Messagebox.INFORMATION);
	}
	
	public static void showMessageError(String mensagem) {
		Messagebox.show(mensagem, null, 0, Messagebox.ERROR);
	}

}
