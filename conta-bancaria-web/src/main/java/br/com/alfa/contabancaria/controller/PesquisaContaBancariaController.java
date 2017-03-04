package br.com.alfa.contabancaria.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Init;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.service.ContaServiceImpl;
import br.com.alfa.contabancaria.vo.ContaVO;

@Controller
public class PesquisaContaBancariaController {
	
	private List<Conta> contas = new ArrayList<>();
	
	@Autowired
	private ContaServiceImpl contaService = new ContaServiceImpl();
	
	@Init
	public void init() {
		try {
			for(ContaVO contaVO : contaService.listar()) {
				contas.add(toModel(contaVO));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private Conta toModel(ContaVO contaVO) {
		Conta conta = new Conta();
		conta.setAgencia(contaVO.getAgencia());
		conta.setBanco(contaVO.getBanco());
		conta.setNumero(contaVO.getNumero());
		conta.setNomeTitular(contaVO.getNomeTitular());
		return conta;
	}
	
	private ContaVO toVO(Conta conta) {
		ContaVO contaVO = new ContaVO();
		contaVO.setAgencia(conta.getAgencia());
		contaVO.setBanco(conta.getBanco());
		contaVO.setNumero(conta.getNumero());
		contaVO.setNomeTitular(conta.getNomeTitular());
		return contaVO;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	
}
