package com.compass.thiagofv.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime dataVenda;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoVenda> vendaProdutos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public List<ProdutoVenda> getVendaProdutos() {
		return vendaProdutos;
	}

	public void setVendaProdutos(List<ProdutoVenda> vendaProdutos) {
		this.vendaProdutos = vendaProdutos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Venda venda = (Venda) o;
		return Objects.equals(id, venda.id) && Objects.equals(dataVenda, venda.dataVenda) && Objects.equals(vendaProdutos, venda.vendaProdutos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dataVenda, vendaProdutos);
	}
}
