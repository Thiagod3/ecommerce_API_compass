package com.compass.thiagofv.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date dataVenda;
	private double valorTotal;
	
	public Venda() {}
	
	public Venda(Date dataVenda, double valorTotal) {
		super();
		this.dataVenda = dataVenda;
		this.valorTotal = valorTotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
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
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
