package com.compass.thiagofv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.thiagofv.domain.Venda;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{
    List<Venda> findByDataVendaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
}
