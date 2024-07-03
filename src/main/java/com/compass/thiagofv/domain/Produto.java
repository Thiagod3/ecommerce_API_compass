package com.compass.thiagofv.domain;

import java.io.Serializable;

public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;
	private Integer estoque;
}
