package com.compass.thiagofv.services;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.domain.Venda;
import com.compass.thiagofv.exceptions.ResourceNotFoundException;
import com.compass.thiagofv.repositories.ProdutoRepository;
import com.compass.thiagofv.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    public Venda create(List<ProdutoVenda> produtosVendas){

        if(produtosVendas.isEmpty()){
            throw new UnsupportedOperationException("Necessario ao menos um produto");
        }

        List<Produto> produtos = new ArrayList<>();

        for (ProdutoVenda produtoVenda : produtosVendas){
            Integer produtoId = produtoVenda.getProdutoId();
            Integer quantidade = produtoVenda.getQuantidade();

            Produto produto = produtoRepo.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

            if(produto.getEstoque() < quantidade){
                throw new UnsupportedOperationException("Estoque insuficiente");
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            produtos.add(produto);
            produtoRepo.save(produto);
        }

        Venda venda = new Venda();
        venda.setDataVenda(new Date());
        venda.setProdutos(produtos);
        return vendaRepo.save(venda);
    }
}
