package com.compass.thiagofv.services;

import java.util.List;

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
		// Verifica se o produto com o ID fornecido existe no repositório
		if (repo.existsById(id)) {
			// Define o ID do produto atualizado para garantir a consistência
			updatedProduto.setId(id);
			// Salva o produto atualizado no repositório
			return repo.save(updatedProduto);
		} else {
			// Caso o produto com o ID fornecido não exista, você pode lançar uma exceção
			// ou retornar null, dependendo da sua lógica de negócio
			return null;
		}
	}



	//registrar produtos
	public Produto create(Produto prod){
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
}
