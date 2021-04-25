package com.danieldhsd.veiculosapi.exception;

public class VeiculoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VeiculoNaoEncontradoException(String message) {
		super(message);
	}
}
