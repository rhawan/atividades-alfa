package br.com.alfa.contabancaria.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericComposer;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.service.ContaServiceImpl;
import br.com.alfa.contabancaria.util.UtilMessageZK;
import br.com.alfa.contabancaria.vo.ContaVO;

@Controller
public class CadastroContaBancariaController extends GenericComposer<Component> {
	
	private static final long serialVersionUID = 7249121532489414914L;

	private Conta contaSelecionada;
	
	private List<Conta> contas = new ArrayList<>();
	
	@Autowired
	private ContaServiceImpl contaService = new ContaServiceImpl();
	
	@Init
	public void init() {
		try {
			this.contaSelecionada = new Conta();
			String idConta = Executions.getCurrent().getParameter("idConta");
			if(!StringUtils.isEmpty(idConta)) {
				contaSelecionada = toModel(contaService.consultar(Long.parseLong(idConta)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Command(value="submit")
	public void salvar() {
		try {
			this.validarCamposObrigatórios();
			contaService.salvar(toVO(contaSelecionada));
			UtilMessageZK.showMessageInfo("Sucesso!");
			Executions.getCurrent().sendRedirect("/pesquisaContaBancaria.zul");
		} catch (Exception e) {
			e.printStackTrace();
			UtilMessageZK.showMessageError("Erro ao salvar conta\n" + e.getMessage());
		}
	}
	
	public List<Conta> listar() {
		try {
			for(ContaVO contaVO : contaService.listar()) {
				contas.add(toModel(contaVO));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return contas;
	}
	
	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}
	
	private void validarCamposObrigatórios() {
		if(contaSelecionada != null && StringUtils.isEmpty(contaSelecionada.getNomeTitular())) {
			throw new IllegalArgumentException("Nome do Titular é obrigatório");
		}
		if(contaSelecionada != null && StringUtils.isEmpty(contaSelecionada.getBanco())) {
			throw new IllegalArgumentException("Nome do Banco é obrigatório");
		}
		if(contaSelecionada != null && StringUtils.isEmpty(contaSelecionada.getAgencia())) {
			throw new IllegalArgumentException("Agência é obrigatório");
		}
		if(contaSelecionada != null && StringUtils.isEmpty(contaSelecionada.getNumero())) {
			throw new IllegalArgumentException("Número da conta é obrigatório");
		}
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
	
	private ContaVO toVO(Conta conta) {
		ContaVO contaVO = new ContaVO();
		contaVO.setId(conta.getId());
		contaVO.setAgencia(conta.getAgencia());
		contaVO.setBanco(conta.getBanco());
		contaVO.setNumero(conta.getNumero());
		contaVO.setNomeTitular(conta.getNomeTitular());
		return contaVO;
	}
}
