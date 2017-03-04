package br.com.alfa.contabancaria.service;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alfa.contabancaria.vo.MovimentacaoVO;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {
	
	private static final String SERVICE_NAME = "MOVIMENTACAO_SERVICE";

	@Override
	public void salvar(MovimentacaoVO conta) throws RemoteException {
		getMovimentacaoServiceRmi().salvar(conta);
	}

	@Override
	public List<MovimentacaoVO> listar() throws RemoteException {
		return getMovimentacaoServiceRmi().listar();
	}

	private MovimentacaoService getMovimentacaoServiceRmi() {
		try {
			Registry registry = LocateRegistry.getRegistry();
			return (MovimentacaoService) registry.lookup(SERVICE_NAME);
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println("Não foi possível conectar no servidor, verifique se o projeto conta-bancaria-persistencia"
					+ " está executando e se a porta está correta!");
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
