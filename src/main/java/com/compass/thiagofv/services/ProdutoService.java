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

	
	//registrar ou atualizar produtos
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
