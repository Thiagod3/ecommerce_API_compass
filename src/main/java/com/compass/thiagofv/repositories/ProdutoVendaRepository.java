package com.compass.thiagofv.repositories;

import com.compass.thiagofv.domain.ProdutoVenda;
import com.compass.thiagofv.domain.ProdutoVendaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, ProdutoVendaPK> {
    boolean existsByProdutoId(Integer produtoId);
}
