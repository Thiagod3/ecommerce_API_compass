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

    public boolean existsById(Integer id) {
        return vendaRepo.existsById(id);
    }

    public Venda create(List<ProdutoVenda> produtosVendas) {
        List<Produto> produtos = utils.VendaUtils.processarProdutosVenda(produtosVendas, produtoRepo);

        Venda venda = new Venda();
        venda.setDataVenda(new Date());
        venda.setProdutos(produtos);
        return vendaRepo.save(venda);
    }


    public List<Venda> getAll(){
        return vendaRepo.findAll();
    }

    public Venda getById(Integer id){
        return vendaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda nao encontrada"));
    }

    public void deleteById(Integer id){
        vendaRepo.deleteById(id);
    }

    public Venda updateById(Integer id, List<ProdutoVenda> produtosVendas){
        if (vendaRepo.existsById(id)) {
            Venda updatedVenda = vendaRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Venda nao encontrada"));


            List<Produto> produtos = utils.VendaUtils.processarProdutosVenda(produtosVendas, produtoRepo);

            updatedVenda.setDataVenda(new Date());
            updatedVenda.setProdutos(produtos);
            return vendaRepo.save(updatedVenda);
        } else {
            return null;
        }
    }
}
