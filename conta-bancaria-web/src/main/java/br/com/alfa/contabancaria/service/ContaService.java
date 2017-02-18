package br.com.alfa.contabancaria.service;

import br.com.alfa.contabancaria.model.Conta;

public interface ContaService {
	
	void salvar(Conta conta);
	
	void listar();
	
	void excluir(Conta conta);

}
