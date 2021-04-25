package com.danieldhsd.veiculosapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.danieldhsd.veiculosapi.model.Veiculo;
import com.danieldhsd.veiculosapi.repository.VeiculosRepository;

@SpringBootTest
class VeiculoControllerTeste {

	private List<Veiculo> veiculos = new ArrayList<>();
	
	@Autowired
	private VeiculosRepository veiculosRepository;
	
	@Test
	void salvarVeiculo() {
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo("Fiat");
		veiculosRepository.save(veiculo);
		
		veiculos = veiculosRepository.findAll();
		
		assertEquals(1, veiculos.size());
		veiculosRepository.deleteAll();
	}
	
	@Test
	void novosVeiculos() {
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo("Fiat");
		veiculosRepository.save(veiculo);
		
		veiculos = veiculosRepository.getAllCadastradosAposData(LocalDateTime.now().minusWeeks(1));
		
		assertEquals(1, veiculos.size());
		veiculosRepository.deleteAll();
	}
	
	@Test
	void naoVendidos() {
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo("Fiat");
		veiculo.setVendido(false);
		veiculosRepository.save(veiculo);
		
		veiculo = new Veiculo();
		veiculo.setVeiculo("Ford");
		veiculo.setVendido(true);
		veiculosRepository.save(veiculo);
		
		Long qtde = veiculosRepository.getQuantidadeNaoVendidos();
		
		assertEquals(1, qtde);
		veiculosRepository.deleteAll();
	}

}
