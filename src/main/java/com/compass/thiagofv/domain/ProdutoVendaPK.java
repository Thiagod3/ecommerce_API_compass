package com.compass.thiagofv.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoVendaPK implements Serializable {

    @Column(name = "venda_id")
    private Integer vendaId;

    @Column(name = "produto_id")
    private Integer produtoId;

    public ProdutoVendaPK(){}

    public ProdutoVendaPK(Integer vendaId, Integer produtoId) {
        this.vendaId = vendaId;
        this.produtoId = produtoId;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoVendaPK that = (ProdutoVendaPK) o;
        return Objects.equals(vendaId, that.vendaId) && Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendaId, produtoId);
    }
}
