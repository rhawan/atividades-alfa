package br.com.alfa.contabancaria.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.alfa.contabancaria.model.Conta;

//@Service
public class ContaServiceImpl extends UnicastRemoteObject implements ContaService {

	private static final long serialVersionUID = -1844440353137518348L;

	protected ContaServiceImpl() throws RemoteException {
		super();
	}
	
	//@PostConstruct
	//public void init() {
	static {
		try {
			ContaServiceImpl contaServiceImpl = new ContaServiceImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind(ContaServiceImpl.class.getSimpleName(), contaServiceImpl);
			System.out.println(ContaServiceImpl.class.getSimpleName() + " carregado no servidor.");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Conta conta) {
		// TODO Executar persistencia
		
	}

	@Override
	public void listar() {
		// TODO Executar persistencia
		
	}

	@Override
	public void excluir(Conta conta) {
		// TODO Executar persistencia
		
	}

}
