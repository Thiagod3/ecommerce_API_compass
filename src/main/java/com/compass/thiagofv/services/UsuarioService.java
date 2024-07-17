package com.compass.thiagofv.services;

import com.compass.thiagofv.domain.Permission;
import com.compass.thiagofv.domain.Usuario;
import com.compass.thiagofv.dto.UsuarioDTO;
import com.compass.thiagofv.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional
    public Usuario cadastrarNovoUsuario(UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
        novoUsuario.setEmail(usuarioDTO.getEmail());

        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        novoUsuario.setAccountNonExpired(true);
        novoUsuario.setAccountNonLocked(true);
        novoUsuario.setCredentialsNonExpired(true);
        novoUsuario.setEnabled(true);

        Permission defaultPermission = new Permission();
        defaultPermission.setId(2);
        novoUsuario.setPermissions(Collections.singletonList(defaultPermission));

        Usuario usuarioSalvo = usuarioRepo.save(novoUsuario);

        return usuarioSalvo;
    }
}
