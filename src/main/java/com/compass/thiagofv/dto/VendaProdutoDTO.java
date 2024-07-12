package com.compass.thiagofv.dto;

import com.compass.thiagofv.domain.ProdutoVenda;

import java.util.List;

public class VendaProdutoDTO {
    private Integer produtoId;
    private Integer quantidade;

    public VendaProdutoDTO(Integer produtoId, Integer quantidade) {
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

