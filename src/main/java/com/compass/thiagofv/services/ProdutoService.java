package com.compass.thiagofv.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.compass.thiagofv.domain.Venda;
import com.compass.thiagofv.exceptions.ExistingProductException;
import com.compass.thiagofv.exceptions.ForbiddenOperationException;
import com.compass.thiagofv.exceptions.ResourceNotFoundException;
import com.compass.thiagofv.utils.ProdutoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private VendaService vendaService;

	public ProdutoService(ProdutoRepository produtoRepo) {
		this.produtoRepo = produtoRepo;
	}

	//verifica se o id existe
	public boolean existsById(Integer id) {
		return produtoRepo.existsById(id);
	}

	// atualizar produto por id
	@CachePut
	public Produto updateById(Integer id, Produto updatedProduto) {
		validateUpdateProduto(updatedProduto, id);
		updatedProduto.setId(id);
		return produtoRepo.save(updatedProduto);
	}

	@CachePut
	public Produto toggleActiveById(Integer id){
		Produto produto = produtoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

		produto.setAtivo(!produto.getAtivo());
		return produtoRepo.save(produto);
	}

	//registrar produtos
	@CacheEvict
	public Produto create(Produto prod){
		validateProduto(prod);
		return produtoRepo.save(prod);
	}
	
	//listar produtos
	@Cacheable("produtosCache")
	public List<Produto> getAll(){
		return produtoRepo.findAll();
	}

	@Cacheable("produtosCache")
	public List<Produto> getAllActives() {
		return produtoRepo.findAll().stream()
				.filter(Produto::getAtivo)
				.collect(Collectors.toList());
	}

	//deletar produtos
	@CacheEvict
	public void deleteById(Integer id) {
		Produto produto = produtoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
		List<Venda> vendas = vendaService.getAll();

		boolean produtoAssociadoEmVenda = vendas.stream().anyMatch(venda -> venda.getProdutos().contains(produto));

		if (produtoAssociadoEmVenda) {
			throw new ForbiddenOperationException("Não é possível excluir o produto porque está associado a uma venda");
		}

		produtoRepo.deleteById(produto.getId());
	}

	//validadores
	private void validateProduto(Produto prod) {
		ProdutoUtils.validateProdutoData(prod);

		List<Produto> produtos = produtoRepo.findAll();

		boolean jaCadastrado = produtos.stream()
				.anyMatch(p -> p.getNome().equalsIgnoreCase(prod.getNome()));
		if (jaCadastrado) {
			throw new ExistingProductException("Produto com nome '" + prod.getNome() + "' já está cadastrado");
		}
	}

	private void validateUpdateProduto(Produto prod, Integer id) {
		ProdutoUtils.validateProdutoData(prod);

		Optional<Produto> esteProdutoOpt = produtoRepo.findById(id);
		if (esteProdutoOpt.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id '" + id + "' não encontrado");
		}

		List<Produto> outrosProdutos = produtoRepo.findByIdNot(id);

		boolean jaCadastrado = outrosProdutos.stream()
				.anyMatch(p -> p.getNome().equalsIgnoreCase(prod.getNome()));
		if (jaCadastrado) {
			throw new ExistingProductException("Produto com nome '" + prod.getNome() + "' já está cadastrado");
		}
	}

}
