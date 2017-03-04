package br.com.alfa.contabancaria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.alfa.contabancaria.vo.TipoMovimentacao;

public class Movimentacao implements Serializable {
	
	private static final long serialVersionUID = -6598038392086700195L;
	
	private BigDecimal valor;
	private TipoMovimentacao tipoMovimentacao;
	private LocalDateTime data = LocalDateTime.now();
	private String descricao;
	private Conta conta;	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public String getDataFormatada() {
		if(data != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			return data.format(formatter);
		}
		return null;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public LocalDateTime getData() {
		return data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
		
}
