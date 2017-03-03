package br.com.alfa.contabancaria.vo;

import java.io.Serializable;

public class ContaVO implements Serializable {
	
	private static final long serialVersionUID = -7262321978508187311L;
	
	private String nomeTitular;
	private String numero;
	private String banco;
	private String agencia;
	
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	
	
}
