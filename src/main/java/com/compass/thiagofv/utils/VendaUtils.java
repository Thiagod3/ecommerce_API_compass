package utils;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.exceptions.InvalidValueException;
import com.compass.thiagofv.exceptions.ResourceNotFoundException;
import com.compass.thiagofv.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;


public class VendaUtils {

    public static List<Produto> processarProdutosVenda(List<ProdutoVenda> produtosVendas, ProdutoRepository produtoRepo) {
        if (produtosVendas.isEmpty()) {
            throw new InvalidValueException("Necessario ao menos um produto");
        }

        List<Produto> produtos = new ArrayList<>();

        for (ProdutoVenda produtoVenda : produtosVendas) {
            Integer produtoId = produtoVenda.getProdutoId();
            Integer quantidade = produtoVenda.getQuantidade();

            Produto produto = produtoRepo.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

            if (produto.getEstoque() < quantidade) {
                throw new InvalidValueException("Estoque insuficiente");
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            if(produto.getEstoque() == 0){
                produto.setAtivo(false);
            }
            produtos.add(produto);
            produtoRepo.save(produto);
        }

        return produtos;
    }

    public static List<Produto> processarAtualizarProdutosVenda(List<ProdutoVenda> produtosVendas, ProdutoRepository produtoRepo) {
        if (produtosVendas.isEmpty()) {
            throw new InvalidValueException("Necessario ao menos um produto");
        }

        List<Produto> produtos = new ArrayList<>();

        for (ProdutoVenda produtoVenda : produtosVendas) {
            Integer produtoId = produtoVenda.getProdutoId();
            Integer quantidade = produtoVenda.getQuantidade();

            Produto produto = produtoRepo.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

            if (produto.getEstoque() < quantidade) {
                throw new InvalidValueException("Estoque insuficiente");
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            produtos.add(produto);
            produtoRepo.save(produto);
        }

        return produtos;
    }
}
