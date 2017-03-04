package br.com.alfa.contabancaria.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.alfa.contabancaria.vo.ContaVO;

public interface ContaService extends Remote {
	
	void salvar(ContaVO conta) throws RemoteException;
	
	List<ContaVO> listar() throws RemoteException;
	
	void excluir(Long id) throws RemoteException;
	
	ContaVO consultar(Long id) throws RemoteException;

}
