package br.com.alfa.contabancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alfa.contabancaria.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	

}
