package com.compass.thiagofv.services;

import com.compass.thiagofv.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        var user = usuarioRepo.findByUsername(username);

        if (user != null){
            return null;
        }else{
            throw new UsernameNotFoundException("Usuario " + username + " nao encontrado!");
        }
    }
}
