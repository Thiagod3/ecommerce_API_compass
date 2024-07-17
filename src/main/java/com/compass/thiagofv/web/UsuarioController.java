package com.compass.thiagofv.web;

import com.compass.thiagofv.domain.Usuario;
import com.compass.thiagofv.dto.UsuarioDTO;
import com.compass.thiagofv.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
            Usuario usuario = usuarioService.cadastrarNovoUsuario(usuarioDTO);
            return ResponseEntity.ok(usuario);
    }
}
