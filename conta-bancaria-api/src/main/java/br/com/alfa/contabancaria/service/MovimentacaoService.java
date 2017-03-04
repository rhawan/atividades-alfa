package br.com.alfa.contabancaria.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.alfa.contabancaria.vo.MovimentacaoVO;

public interface MovimentacaoService extends Remote {
	
	void salvar(MovimentacaoVO conta) throws RemoteException;
	
	List<MovimentacaoVO> listar() throws RemoteException;
}
