package br.com.alfa.contabancaria.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentacaoVO implements Serializable {
	
	private static final long serialVersionUID = -6598038392086700195L;
	
	private BigDecimal valor;
	private TipoMovimentacao tipoMovimentacao;
	private LocalDateTime data;
	private String descricao;
	private ContaVO conta;
	
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
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ContaVO getConta() {
		return conta;
	}
	public void setConta(ContaVO conta) {
		this.conta = conta;
	}
	
	
		
}
