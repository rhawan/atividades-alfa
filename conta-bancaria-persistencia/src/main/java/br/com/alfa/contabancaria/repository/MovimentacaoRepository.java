package br.com.alfa.contabancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alfa.contabancaria.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
	

}
