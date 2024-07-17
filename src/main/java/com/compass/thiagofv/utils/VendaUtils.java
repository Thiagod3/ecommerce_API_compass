package com.compass.thiagofv.utils;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.domain.ProdutoVendaPK;
import com.compass.thiagofv.domain.Venda;
import com.compass.thiagofv.dto.VendaProdutoDTO;
import com.compass.thiagofv.exceptions.InvalidValueException;
import com.compass.thiagofv.exceptions.ResourceNotFoundException;
import com.compass.thiagofv.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class VendaUtils {

    public static List<ProdutoVenda> processarProdutosVenda(
            List<VendaProdutoDTO> vendaProdutoDTOs, ProdutoRepository produtoRepo, Venda venda) {
        if (vendaProdutoDTOs.isEmpty()) {
            throw new InvalidValueException("Necessário ao menos um produto");
        }

        List<ProdutoVenda> vendaProdutos = new ArrayList<>();

        for (VendaProdutoDTO vendaProdutoDTO : vendaProdutoDTOs) {
            Integer produtoId = vendaProdutoDTO.getProdutoId();
            int quantidade = vendaProdutoDTO.getQuantidade();

            Produto produto = produtoRepo.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            if (produto.getEstoque() < quantidade) {
                throw new InvalidValueException("Estoque insuficiente");
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            if (produto.getEstoque() == 0) {
                produto.setAtivo(false);
            }
            produtoRepo.save(produto);

            ProdutoVendaPK id = new ProdutoVendaPK(venda.getId(), produto.getId());
            ProdutoVenda vendaProduto = new ProdutoVenda();
            vendaProduto.setId(id);
            vendaProduto.setVenda(venda);
            vendaProduto.setProduto(produto);
            vendaProduto.setQuantidade(quantidade);

            vendaProdutos.add(vendaProduto);
        }

        return vendaProdutos;
    }


    public static List<ProdutoVenda> processarAtualizarProdutosVenda(
            List<VendaProdutoDTO> vendaProdutoDTOs, ProdutoRepository produtoRepo, Venda venda) {
        if (vendaProdutoDTOs.isEmpty()) {
            throw new InvalidValueException("Necessário ao menos um produto");
        }

        List<ProdutoVenda> vendaProdutos = new ArrayList<>();

        for (VendaProdutoDTO vendaProdutoDTO : vendaProdutoDTOs) {
            Integer produtoId = vendaProdutoDTO.getProdutoId();
            int quantidade = vendaProdutoDTO.getQuantidade();

            Produto produto = produtoRepo.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            ProdutoVendaPK id = new ProdutoVendaPK(venda.getId(), produto.getId());

            // Verifica se já existe uma relação entre a venda e o produto
            Optional<ProdutoVenda> existingVendaProduto = venda.getVendaProdutos().stream()
                    .filter(vp -> vp.getId().equals(id))
                    .findFirst();

            if (existingVendaProduto.isPresent()) {
                // Atualiza a quantidade existente e ajusta o estoque
                ProdutoVenda vendaProduto = existingVendaProduto.get();
                int quantidadeAnterior = vendaProduto.getQuantidade();
                int diferenca = quantidade - quantidadeAnterior;

                if (produto.getEstoque() < diferenca) {
                    throw new InvalidValueException("Estoque insuficiente");
                }

                produto.setEstoque(produto.getEstoque() - diferenca);
                vendaProduto.setQuantidade(quantidade);
            } else {
                // Cria uma nova relação e ajusta o estoque
                if (produto.getEstoque() < quantidade) {
                    throw new InvalidValueException("Estoque insuficiente");
                }

                produto.setEstoque(produto.getEstoque() - quantidade);
                ProdutoVenda vendaProduto = new ProdutoVenda();
                vendaProduto.setId(id);
                vendaProduto.setVenda(venda);
                vendaProduto.setProduto(produto);
                vendaProduto.setQuantidade(quantidade);

                vendaProdutos.add(vendaProduto);
            }

            if (produto.getEstoque() == 0) {
                produto.setAtivo(false);
            }

            produtoRepo.save(produto);
        }

        // Adiciona as relações existentes que não foram atualizadas
        venda.getVendaProdutos().forEach(vp -> {
            if (!vendaProdutos.contains(vp)) {
                vendaProdutos.add(vp);
            }
        });

        return vendaProdutos;
    }

}
