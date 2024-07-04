package com.compass.thiagofv.dto;

import com.compass.thiagofv.domain.ProdutoVenda;

import java.util.List;

public class VendaRequest {
    private List<ProdutoVenda> produtos;

    // Construtor padrão
    public VendaRequest() {
    }

    // Construtor com parâmetros
    public VendaRequest(List<ProdutoVenda> produtos) {
        this.produtos = produtos;
    }

    // Getters e setters
    public List<ProdutoVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoVenda> produtos) {
        this.produtos = produtos;
    }
}

