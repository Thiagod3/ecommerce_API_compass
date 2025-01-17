package com.compass.thiagofv.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	//endpoint criacao
	@PostMapping("/criar")
	public ResponseEntity<Produto> create(@RequestBody Produto prod) {
		Produto prodCriado = produtoService.create(prod);
		return ResponseEntity.status(HttpStatus.CREATED).body(prodCriado);
	}

	//endpoint atualizacao
	@PatchMapping("/atualizar/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto updatedProduto) {
		Produto produto = produtoService.updateById(id, updatedProduto);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/desativar/{id}")
	public ResponseEntity<Produto> inactiveProduto(@PathVariable Integer id){
		Produto produto = produtoService.toggleActiveById(id);
		return ResponseEntity.ok(produto);
	}

	//endpoint listagem
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		List<Produto> produtos = produtoService.getAll();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/ativos")
	public ResponseEntity<List<Produto>> getAllActive(){
		List<Produto> produtosAtivos = produtoService.getAllActives();
		return ResponseEntity.ok(produtosAtivos);
	}

	//endpoint deletar
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable("id") Integer id){
            produtoService.deleteById(id);
            return ResponseEntity.noContent().build();
	}
}
