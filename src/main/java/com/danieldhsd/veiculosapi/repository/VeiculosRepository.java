package com.danieldhsd.veiculosapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danieldhsd.veiculosapi.model.Veiculo;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculo, Long> {

	public List<Veiculo> findByVeiculo(String name);
	
	@Query("SELECT v FROM Veiculo v WHERE lower(v.veiculo) like lower(:name)")
	public List<Veiculo> findVeiculoByVeiculo(@Param("name") String name);
	
	@Query("SELECT count(v) FROM Veiculo v WHERE v.vendido = false")
	public Long getQuantidadeNaoVendidos();
	
	@Query("SELECT new com.danieldhsd.veiculosapi.model.Veiculo(count(v), v.marca) FROM Veiculo v group by v.marca")
	public List<Veiculo> getVeiculosAgrupadoPorFabricante();
	
	@Query("SELECT v FROM Veiculo v WHERE v.created > :data")
	public List<Veiculo> getAllCadastradosAposData(LocalDateTime data);
}
