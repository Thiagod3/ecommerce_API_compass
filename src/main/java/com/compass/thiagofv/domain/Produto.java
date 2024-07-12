package com.compass.thiagofv.domain;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.*;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private Double preco;
	private Integer estoque;
	private Boolean ativo = true;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoVenda> vendaProdutos = new ArrayList<>();

	public Produto() {}
	
	public Produto(String nome, Double preco, Integer estoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;

		if(estoque == 0){
			this.ativo = false;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;

		if(estoque == 0){
			this.ativo = false;
		}
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<ProdutoVenda> getVendaProdutos() {
		return vendaProdutos;
	}

	public void setVendaProdutos(List<ProdutoVenda> vendaProdutos) {
		this.vendaProdutos = vendaProdutos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
