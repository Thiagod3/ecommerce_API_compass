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
}
