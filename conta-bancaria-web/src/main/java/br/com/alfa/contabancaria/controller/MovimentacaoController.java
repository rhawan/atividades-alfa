package br.com.alfa.contabancaria.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.model.Movimentacao;
import br.com.alfa.contabancaria.service.ContaServiceImpl;
import br.com.alfa.contabancaria.service.MovimentacaoServiceImpl;
import br.com.alfa.contabancaria.util.UtilMessageZK;
import br.com.alfa.contabancaria.vo.ContaVO;
import br.com.alfa.contabancaria.vo.MovimentacaoVO;
import br.com.alfa.contabancaria.vo.TipoMovimentacao;

@Controller
public class MovimentacaoController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 8001874749532724748L;

	private ListModel<Movimentacao> movimentacoes;
	
	private Movimentacao movimentacao;
	
	@Wire
	private Window window;
	
	@Autowired
	private MovimentacaoServiceImpl movimentacaoService = new MovimentacaoServiceImpl();
	
	@Autowired
	private ContaServiceImpl contaService = new ContaServiceImpl();
	
	public MovimentacaoController() {
		this.listar();
		this.movimentacao = new Movimentacao();
	}
	
	@NotifyChange("movimentacoes")
	@Command(value="submit")
	public void salvar() {
		try {
			this.validarCamposObrigatórios();
			movimentacaoService.salvar(toVO(movimentacao));
			UtilMessageZK.showMessageInfo("Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			UtilMessageZK.showMessageError("Erro ao salvar movimentação\n" + e.getMessage());
		}
	}
	
	public ListModelList<TipoMovimentacao> getTiposMovimentacao() {
		return new ListModelList<>(TipoMovimentacao.values());
	}
	
	public ListModelList<Conta> getContas() {
		try {
			List<Conta> contas = new ArrayList<>();
			for(ContaVO contaVO : contaService.listar()) {
				contas.add(toModel(contaVO));
			}
			return new ListModelList<>(contas);
		} catch (RemoteException e) {
			e.printStackTrace();
			UtilMessageZK.showMessageError("Erro ao listar contas\n" + e.getMessage());
		}
		return null;
	}
	
	private void listar() {
		try {
			List<Movimentacao> movimentacoesEntity = new ArrayList<>();
			for(MovimentacaoVO movimentacaoVO : movimentacaoService.listar()) {
				movimentacoesEntity.add(toModel(movimentacaoVO));
			}
			movimentacoes = new ListModelList<>(movimentacoesEntity);
		} catch (RemoteException e) {
			e.printStackTrace();
			UtilMessageZK.showMessageError("Erro ao listar movimentações\n" + e.getMessage());
		}
	}
	
	@Command(value = "novaMovimentacao")
	public void showDialogCadastro() {
		window = (Window) Executions.createComponents("dialogCadastroMovimentacao.zul", null, null);
		window.doModal();
	}
	
	private void validarCamposObrigatórios() {
		if(movimentacao != null && StringUtils.isEmpty(movimentacao.getDescricao())) {
			throw new IllegalArgumentException("Descrição é obrigatório");
		}
		if(movimentacao != null && StringUtils.isEmpty(movimentacao.getTipoMovimentacao())) {
			throw new IllegalArgumentException("Tipo Movimentação é obrigatório");
		}
		if(movimentacao != null && StringUtils.isEmpty(movimentacao.getValor())) {
			throw new IllegalArgumentException("Valor é obrigatório");
		}
		if(movimentacao != null && movimentacao.getConta() == null) {
			throw new IllegalArgumentException("Conta Bancária é obrigatório");
		}
	}

	
	private MovimentacaoVO toVO(Movimentacao movimentacao) {
		MovimentacaoVO mVO = new MovimentacaoVO();
		mVO.setConta(toVO(movimentacao.getConta()));
		mVO.setData(movimentacao.getData());
		mVO.setDescricao(movimentacao.getDescricao());
		mVO.setTipoMovimentacao(movimentacao.getTipoMovimentacao());
		mVO.setValor(movimentacao.getValor());
		return mVO;
	}
	
	private Movimentacao toModel(MovimentacaoVO mVO) {
		Movimentacao mov = new Movimentacao();
		mov.setConta(toModel(mVO.getConta()));
		mov.setData(mVO.getData());
		mov.setDescricao(mVO.getDescricao());
		mov.setTipoMovimentacao(mVO.getTipoMovimentacao());
		mov.setValor(mVO.getValor());
		return mov;
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

	public ListModel<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(ListModel<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
}
