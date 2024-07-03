package com.compass.thiagofv.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;

public class ProdutoVenda implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutoVendaPK id = new ProdutoVendaPK();
	
	private Integer quantidade;
	private Double precoUn;
	
	
	public ProdutoVenda(Produto produto, Venda venda, Integer quantidade, Double precoUn) {
		id.setProduto(produto);
		id.setVenda(venda);
		this.quantidade = quantidade;
		this.precoUn = precoUn;
	}


	public Produto getProduto() {
		return id.getProduto();
	}
	
	public Venda getVenda() {
		return id.getVenda();
	}

	public ProdutoVendaPK getId() {
		return id;
	}


	public void setId(ProdutoVendaPK id) {
		this.id = id;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPrecoUn() {
		return precoUn;
	}


	public void setPrecoUn(Double precoUn) {
		this.precoUn = precoUn;
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
		ProdutoVenda other = (ProdutoVenda) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
