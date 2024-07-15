package com.compass.thiagofv.repositories;

import com.compass.thiagofv.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.nomeUsuario = :userName")
    Usuario findByUsername(@Param("userName") String userName);
}
