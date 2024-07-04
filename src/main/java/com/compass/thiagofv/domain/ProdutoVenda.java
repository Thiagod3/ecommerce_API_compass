package com.compass.thiagofv.domain;

import java.io.Serializable;

public class ProdutoVenda {
	private Integer produtoId;
	private Integer quantidade;


	public ProdutoVenda(){}

	public ProdutoVenda(Integer produtoId, Integer quantidade) {
		this.produtoId = produtoId;
		this.quantidade = quantidade;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
