package br.com.alfa.contabancaria.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.service.ContaServiceImpl;
import br.com.alfa.contabancaria.util.UtilMessageZK;
import br.com.alfa.contabancaria.vo.ContaVO;

@Controller
public class PesquisaContaBancariaController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = -3927700515591273703L;

	private ListModel<Conta> contas;
	
	@Autowired
	private ContaServiceImpl contaService = new ContaServiceImpl();
	
	public PesquisaContaBancariaController() {
		this.listar();
	}
	
	@Command(value="excluir")
	@NotifyChange("contas")
	public void excluir(@BindingParam("conta") Conta conta) {
		try {
			contaService.excluir(conta.getId());
			this.listar();
		} catch (RemoteException e) {
			e.printStackTrace();
			UtilMessageZK.showMessageError("Erro ao excluir conta\n" + e.getMessage());
		}
	}
	
	private void listar() {
		try {
			List<Conta> contasEntity = new ArrayList<>();
			for(ContaVO contaVO : contaService.listar()) {
				contasEntity.add(toModel(contaVO));
			}
			contas = new ListModelList<>(contasEntity);
		} catch (RemoteException e) {
			e.printStackTrace();
			UtilMessageZK.showMessageError("Erro ao listar contas\n" + e.getMessage());
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
	
	public ListModel<Conta> getContas() {
		return contas;
	}

	public void setContas(ListModel<Conta> contas) {
		this.contas = contas;
	}
	
	
}
