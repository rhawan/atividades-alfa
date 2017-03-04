package br.com.alfa.contabancaria.service;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.repository.ContaRepository;
import br.com.alfa.contabancaria.vo.ContaVO;

@Service
public class ContaServiceImpl extends UnicastRemoteObject implements ContaService {

	private static final long serialVersionUID = 4857666947078681491L;
	
	private static final String SERVICE_NAME = "CONTA_SERVICE";
	
	@Autowired
	private ContaRepository contaRepository;

	public ContaServiceImpl() throws RemoteException {
		super();
	}
	
	@PostConstruct
	public void init() {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind(SERVICE_NAME, this);
			System.out.println(SERVICE_NAME + " carregado no servidor.");
			this.inserirContasTeste();
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(ContaVO conta) throws RemoteException {
		contaRepository.save(toModel(conta));
	}

	@Override
	public void excluir(Long id) throws RemoteException {
		contaRepository.delete(id);
	}
	
	@Override
	public List<ContaVO> listar() throws RemoteException {
		 List<ContaVO> contasVO = new ArrayList<>();
		 for (Conta conta : contaRepository.findAll()) {
			 contasVO.add(toVO(conta));
		 }
		 return contasVO;
	}
	
	@Override
	public ContaVO consultar(Long id) throws RemoteException {
		return toVO(contaRepository.findOne(id));
	}
	
	private void inserirContasTeste() {
		Conta c1 = new Conta();
		c1.setAgencia("1234");
		c1.setBanco("Itaú");
		c1.setNumero("21");
		c1.setNomeTitular("JOÃO SILVA SAURO");
		
		Conta c2 = new Conta();
		c2.setAgencia("6789");
		c2.setBanco("Bradesco");
		c2.setNumero("54");
		c2.setNomeTitular("MARIA JOANINHA");
		
		contaRepository.save(c1);
		contaRepository.save(c2);
	}
	
	private ContaVO toVO(Conta conta) {
		ContaVO contaVO = new ContaVO();
		contaVO.setId(conta.getId());
		contaVO.setAgencia(conta.getAgencia());
		contaVO.setBanco(conta.getBanco());
		contaVO.setNumero(conta.getNumero());
		contaVO.setNomeTitular(conta.getNomeTitular());
		return contaVO;
	}
	
	private Conta toModel(ContaVO contaVO) {
		Conta conta = new Conta();
		conta.setId(contaVO.getId());
		conta.setAgencia(contaVO.getAgencia());
		conta.setBanco(contaVO.getBanco());
		conta.setNumero(contaVO.getNumero());
		conta.setNomeTitular(contaVO.getNomeTitular());
		return conta;
	}
	
}
