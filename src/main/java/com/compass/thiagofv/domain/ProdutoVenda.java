package com.compass.thiagofv.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class ProdutoVenda {

	@JsonIgnore
	@EmbeddedId
	private ProdutoVendaPK id;

	@ManyToOne
	@MapsId("vendaId")
	@JoinColumn(name = "venda_id")
	private Venda venda;

	@ManyToOne
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id")
	private Produto produto;

	private Integer quantidade;


	public ProdutoVenda(){}

	public ProdutoVenda(Integer quantidade, Produto produto, Venda venda) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.venda = venda;
	}

	public ProdutoVendaPK getId() {
		return id;
	}

	public void setId(ProdutoVendaPK produtoId) {
		this.id = produtoId;
	}

	@JsonIgnore
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@JsonIgnore
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
