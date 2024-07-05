package com.compass.thiagofv.web;

import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.domain.Venda;
import com.compass.thiagofv.dto.VendaRequest;
import com.compass.thiagofv.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody VendaRequest vendaRequest){
        List<ProdutoVenda> produtoVendas = vendaRequest.getProdutos();
        Venda vendaCriada = vendaService.create(produtoVendas);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaCriada);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> getAll(){
        List<Venda> vendas = vendaService.getAll();
        return ResponseEntity.ok(vendas);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable("id") Integer id){
        if(vendaService.existsById(id)){
            vendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
