package com.compass.thiagofv.web;

import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.domain.Venda;
import com.compass.thiagofv.dto.VendaRequest;
import com.compass.thiagofv.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    //criação
    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody VendaRequest vendaRequest){
        List<ProdutoVenda> produtoVendas = vendaRequest.getProdutos();
        Venda vendaCriada = vendaService.create(produtoVendas);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaCriada);
    }


    //listagem
    @GetMapping
    public ResponseEntity<List<Venda>> getAll(){
        List<Venda> vendas = vendaService.getAll();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/datas/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Venda>> getBetweenDate(
            @PathVariable("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @PathVariable("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

        return ResponseEntity.ok(vendaService.getBetweenDate(dataInicial, dataFinal));
    }

    @GetMapping("/semana")
    public ResponseEntity<List<Venda>> getLastWeek(){
        List<Venda> vendasLW = vendaService.getLastWeek();
        return ResponseEntity.ok(vendasLW);
    }

    @GetMapping("/mes/{ano}/{mes}")
    public ResponseEntity<List<Venda>> getByMonth(@PathVariable("ano") int ano,
                                                  @PathVariable("mes") int mes){
        List<Venda> vendasM = vendaService.getByMonth(ano, Month.of(mes));
        return ResponseEntity.ok(vendasM);
    }


    //deletar
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable("id") Integer id){
        if(vendaService.existsById(id)){
            vendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //atualizar
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Venda> update(@PathVariable("id") Integer id, @RequestBody VendaRequest vendaRequest){
        List<ProdutoVenda> novosProdutosVendas = vendaRequest.getProdutos();
        Venda venda = vendaService.updateById(id, novosProdutosVendas);
        if (venda != null) {
            return ResponseEntity.ok(venda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
