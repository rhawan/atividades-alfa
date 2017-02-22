package br.com.alfa.contabancaria.service;

import java.rmi.Remote;

import br.com.alfa.contabancaria.model.Conta;

public interface ContaService extends Remote {
	
	void salvar(Conta conta);
	
	void listar();
	
	void excluir(Conta conta);

}
