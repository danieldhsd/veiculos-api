package com.danieldhsd.veiculosapi.model;

import java.time.LocalDateTime;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String veiculo;
	
	private String marca;
	
	private Year ano;
	
	@Column(length = 1000)
	private String descricao;
	
	private Boolean vendido;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
	
	@Transient
	private Long quantidadePorFabricante;
	
	@PrePersist
	private void prePersist() {
		this.created = LocalDateTime.now();
		this.updated = LocalDateTime.now();
	}
	
	@PreUpdate
	private void preUpdate() {
		this.updated = LocalDateTime.now();
	}
	
	public Veiculo() {}
	
	public Veiculo(Long quantidadePorFabricante, String marca) {
		this.marca = marca;
		this.quantidadePorFabricante = quantidadePorFabricante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Year getAno() {
		return ano;
	}

	public void setAno(Year ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public Long getQuantidadePorFabricante() {
		return quantidadePorFabricante;
	}

	public void setQuantidadePorFabricante(Long quantidadePorFabricante) {
		this.quantidadePorFabricante = quantidadePorFabricante;
	}
}
