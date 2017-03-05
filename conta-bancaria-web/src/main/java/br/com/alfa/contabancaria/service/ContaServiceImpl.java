package br.com.alfa.contabancaria.service;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	public void excluir(Long id) throws RemoteException {
		getContaServiceRmi().excluir(id);
		
	}
	
	@Override
	public ContaVO consultar(Long id) throws RemoteException {
		return getContaServiceRmi().consultar(id);
	}
	
	private ContaService getContaServiceRmi() {
		try {
			Properties props = new Properties();
			InputStream is = MovimentacaoServiceImpl.class.getClassLoader().getResourceAsStream("application.properties");
			props.load(is);
			
			String rmiPortProperty = props.getProperty("rmi.port");
			String rmiHostProperty = props.getProperty("rmi.host");
			
			if(StringUtils.isEmpty(rmiPortProperty)) {
				System.err.println("A propriedade rmi.port não está definida corretamente no arquivo application.properties!!");
				return null;
			}
			if(StringUtils.isEmpty(rmiHostProperty)) {
				System.err.println("A propriedade rmi.host não está definida corretamente no arquivo application.properties!!");
				return null;
			}
			Registry registry = LocateRegistry.getRegistry(rmiHostProperty, Integer.parseInt(rmiPortProperty));
			return (ContaService) registry.lookup(SERVICE_NAME);
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println("Não foi possível conectar no servidor, verifique se o projeto conta-bancaria-persistencia"
					+ " está executando e se a porta está correta!");
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
