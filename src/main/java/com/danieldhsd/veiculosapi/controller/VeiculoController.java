package com.danieldhsd.veiculosapi.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danieldhsd.veiculosapi.exception.VeiculoNaoEncontradoException;
import com.danieldhsd.veiculosapi.model.Veiculo;
import com.danieldhsd.veiculosapi.repository.VeiculosRepository;

@RestController
@CrossOrigin
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculosRepository veiculosRepository;
	
	@GetMapping
	@CrossOrigin
	public List<Veiculo> getAllVeiculos() {
		return veiculosRepository.findAll();
	}
	
	@GetMapping("/find")
	public List<Veiculo> findAllByVeiculo(@RequestParam String q) {
		return veiculosRepository.findVeiculoByVeiculo("%" + q + "%");
	}
	
	@GetMapping("/{id}")
	public Veiculo findVeiculoById(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculosRepository.findById(id);
		if(!veiculo.isPresent())
			throw new VeiculoNaoEncontradoException("Veiculo não encontrado.");
		
		return veiculo.get();
	}
	
	@GetMapping("/naoVendidos")
	public Long getNaoVendidos() {
		return veiculosRepository.getQuantidadeNaoVendidos();
	}
	
	@GetMapping("/porFabricante")
	public List<Veiculo> getAgrupadoPorFabricante() {
		return veiculosRepository.getVeiculosAgrupadoPorFabricante();
	}
	
	@GetMapping("/novos")
	public List<Veiculo> getCadastradosNaUltimaSemana() {
		LocalDateTime data = LocalDateTime.now().minusWeeks(1);
		return veiculosRepository.getAllCadastradosAposData(data);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo adicionarVeiculo(@RequestBody Veiculo veiculo) {
		return veiculosRepository.save(veiculo);
	}
	
	@PutMapping("/{id}")
	public Veiculo atualizarVeiculo(@RequestBody Veiculo veiculo, @PathVariable long id) {
		Optional<Veiculo> veiculoOptional = veiculosRepository.findById(id);
		
		if(!veiculoOptional.isPresent())
			throw new VeiculoNaoEncontradoException("Veiculo não encontrado.");
		
		veiculo.setId(id);
		veiculo.setCreated(veiculoOptional.get().getCreated());
		
		return veiculosRepository.save(veiculo);
	}
	
	@PatchMapping("/{id}")
	public Veiculo atualizaPartesVeiculo(@RequestBody Veiculo veiculo, @PathVariable long id) {
		Optional<Veiculo> veiculoOptional = veiculosRepository.findById(id);
		
		if(!veiculoOptional.isPresent())
			throw new VeiculoNaoEncontradoException("Veiculo não encontrado.");
		
		Veiculo veiculoExistente = veiculoOptional.get();
		veiculoExistente.setVeiculo(veiculo.getVeiculo());
		
		return veiculosRepository.save(veiculoExistente);
	}
	
	@DeleteMapping("/{id}")
	public void apagarVeiculo(@PathVariable Long id) {
		veiculosRepository.deleteById(id);
	}
}
