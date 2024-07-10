package com.compass.thiagofv.services;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.domain.Venda;
import com.compass.thiagofv.exceptions.ResourceNotFoundException;
import com.compass.thiagofv.repositories.ProdutoRepository;
import com.compass.thiagofv.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public boolean existsById(Integer id) {
        return vendaRepo.existsById(id);
    }


    //criar vendas
    @CacheEvict
    public Venda create(List<ProdutoVenda> produtosVendas) {
        List<Produto> produtos = utils.VendaUtils.processarProdutosVenda(produtosVendas, produtoRepo);

        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        venda.setProdutos(produtos);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String dataFormatada = venda.getDataVenda().format(formatter);
        System.out.println("Data formatada no padrão ISO 8601: " + dataFormatada);

        return vendaRepo.save(venda);
    }

    //listar vendas
    @Cacheable("vendasCache")
    public List<Venda> getAll(){
        return vendaRepo.findAll();
    }

    @Cacheable("vendasCache")
    public Venda getById(Integer id){
        return vendaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda nao encontrada"));
    }

    @Cacheable("vendasCache")
    public List<Venda> getBetweenDate(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<Venda> vendas = vendaRepo.findByDataVendaBetween(dataInicial, dataFinal);

        if (vendas.isEmpty()){
            throw new ResourceNotFoundException("Vendas não encontradas");
        }
        return vendaRepo.findByDataVendaBetween(dataInicial, dataFinal);
    }

    @Cacheable("vendasCache")
    public List<Venda> getLastWeek() {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataInicial = dataAtual.minusDays(7);
        return vendaRepo.findByDataVendaBetween(dataInicial, dataAtual);
    }

    @Cacheable("vendasCache")
    public List<Venda> getByMonth(int ano, Month mes) {
        YearMonth anoMes = YearMonth.of(ano, mes);
        LocalDateTime primeiroDiaMes = anoMes.atDay(1).atStartOfDay();
        LocalDateTime ultimoDiaMes = anoMes.atEndOfMonth().atTime(23, 59, 59);
        return vendaRepo.findByDataVendaBetween(primeiroDiaMes, ultimoDiaMes);
    }


    //deletar vendas
    @CacheEvict
    public void deleteById(Integer id){
        vendaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID '" + id + "' não encontrado!"));
        vendaRepo.deleteById(id);
    }


    //atualizar vendas
    @CachePut
    public Venda updateById(Integer id, List<ProdutoVenda> produtosVendas){
        Venda updatedVenda = vendaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda nao encontrada"));


        List<Produto> produtos = utils.VendaUtils.processarProdutosVenda(produtosVendas, produtoRepo);

        updatedVenda.setDataVenda(LocalDateTime.now());
        updatedVenda.setProdutos(produtos);
        return vendaRepo.save(updatedVenda);
    }
}
