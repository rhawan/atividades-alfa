package br.com.alfa.contabancaria.service;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alfa.contabancaria.vo.ContaVO;

@Service
public class ContaServiceImpl implements ContaService {
	
	private static final String SERVICE_NAME = "CONTA_SERVICE";

	@Override
	public void salvar(ContaVO conta) throws RemoteException {
		getContaServiceRmi().salvar(conta);
	}

	@Override
	public List<ContaVO> listar() throws RemoteException {
		return getContaServiceRmi().listar();
	}

	@Override
	public void excluir(ContaVO conta) throws RemoteException {
		getContaServiceRmi().excluir(conta);
		
	}
	
	private ContaService getContaServiceRmi() {
		try {
			Registry registry = LocateRegistry.getRegistry();
			return (ContaService) registry.lookup(SERVICE_NAME);
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
