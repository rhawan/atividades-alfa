package br.com.alfa.contabancaria.controller;

import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.service.ContaService;
import br.com.alfa.contabancaria.service.ContaServiceImpl;

@Controller
public class CadastroContaBancariaController {
	
	private Conta contaSelecionada;
	
	private ContaService contaService = new ContaServiceImpl();
	
	@Init
	public void init() {
		this.contaSelecionada = new Conta();
	}
		
	@Command(value="submit")
	public void salvar() {
		contaService.salvar(contaSelecionada);
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}
	
}
