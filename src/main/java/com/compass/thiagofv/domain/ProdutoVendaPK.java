package com.compass.thiagofv.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProdutoVendaPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	
	private Venda venda;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(produto, venda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVendaPK other = (ProdutoVendaPK) obj;
		return Objects.equals(produto, other.produto) && Objects.equals(venda, other.venda);
	}
	
	

}
