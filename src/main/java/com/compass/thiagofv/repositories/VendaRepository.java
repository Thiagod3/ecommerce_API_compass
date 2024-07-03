package com.compass.thiagofv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.thiagofv.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{

}
