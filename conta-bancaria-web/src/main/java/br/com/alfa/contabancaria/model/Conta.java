package br.com.alfa.contabancaria.model;

import java.io.Serializable;

public class Conta implements Serializable {
	
	private static final long serialVersionUID = -128747010897663148L;
	
	private Long id;
	private String nomeTitular;
	private String numero;
	private String banco;
	private String agencia;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	@Override
	public String toString() {
		return String.format("%s - (%s - %s)", nomeTitular, agencia, numero);
	}
	
}
