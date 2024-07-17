package com.compass.thiagofv.repositories;

import com.compass.thiagofv.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByLogin(String login);

    Usuario findByEmail(String email);
}

