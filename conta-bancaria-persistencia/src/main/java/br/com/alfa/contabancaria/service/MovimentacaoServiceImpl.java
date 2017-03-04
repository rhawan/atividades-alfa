package br.com.alfa.contabancaria.service;

import java.math.BigDecimal;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alfa.contabancaria.model.Conta;
import br.com.alfa.contabancaria.model.Movimentacao;
import br.com.alfa.contabancaria.repository.ContaRepository;
import br.com.alfa.contabancaria.repository.MovimentacaoRepository;
import br.com.alfa.contabancaria.vo.ContaVO;
import br.com.alfa.contabancaria.vo.MovimentacaoVO;
import br.com.alfa.contabancaria.vo.TipoMovimentacao;

@Service
public class MovimentacaoServiceImpl extends UnicastRemoteObject implements MovimentacaoService {

	private static final long serialVersionUID = 6690500500955316563L;

	private static final String SERVICE_NAME = "MOVIMENTACAO_SERVICE";
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaRepository contaRepository;

	public MovimentacaoServiceImpl() throws RemoteException {
		super();
	}
	
	@PostConstruct
	public void init() {
		try {
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(SERVICE_NAME, this);
			System.out.println(SERVICE_NAME + " carregado no servidor.");
			this.inserirMovimentacoesTeste();
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(MovimentacaoVO movimentacao) throws RemoteException {
		movimentacaoRepository.save(toModel(movimentacao));
	}

	@Override
	public List<MovimentacaoVO> listar() throws RemoteException {
		List<MovimentacaoVO> movimentacoesVO = new ArrayList<>();
		for(Movimentacao mov : movimentacaoRepository.findAll()) {
			movimentacoesVO.add(toVO(mov));
		}
		return movimentacoesVO;
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
	
	private void inserirMovimentacoesTeste() {
		Movimentacao m1 = new Movimentacao();
		m1.setData(LocalDateTime.now());
		m1.setDescricao("Saque");
		m1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		m1.setValor(new BigDecimal("25.36"));
		m1.setConta(contaRepository.findOne(1L));
		
		movimentacaoRepository.save(m1);
		
	}
	
}
