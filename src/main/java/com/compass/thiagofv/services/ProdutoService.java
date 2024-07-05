package com.compass.thiagofv.services;

import java.util.List;

import com.compass.thiagofv.exceptions.ExistingProductException;
import com.compass.thiagofv.utils.ProdutoUtils;
import org.springframework.stereotype.Service;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository repo;

	public ProdutoService(ProdutoRepository repo) {
		this.repo = repo;
	}

	//verifica se o id existe
	public boolean existsById(Integer id) {
		return repo.existsById(id);
	}

	// atualizar produto por id
	public Produto updateById(Integer id, Produto updatedProduto) {
		if (repo.existsById(id)) {
			validateProduto(updatedProduto);
			updatedProduto.setId(id);
			return repo.save(updatedProduto);
		} else {
			return null;
		}
	}

	//registrar produtos
	public Produto create(Produto prod){
		validateProduto(prod);
		return repo.save(prod);
	}
	
	//listar produtos
	public List<Produto> getAll() {
		return repo.findAll();
	}

	
	//deletar produtos
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	private void validateProduto(Produto prod) {
		ProdutoUtils.validateProdutoData(prod);

		List<Produto> produtos = repo.findAll();

		boolean jaCadastrado = produtos.stream()
				.anyMatch(p -> p.getNome().equalsIgnoreCase(prod.getNome()));
		if (jaCadastrado) {
			throw new ExistingProductException("Produto com nome '" + prod.getNome() + "' já está cadastrado");
		}
	}
}
