package com.compass.thiagofv.services;

import com.compass.thiagofv.domain.vo.CredencialVO;
import com.compass.thiagofv.domain.vo.TokenVO;
import com.compass.thiagofv.repositories.UsuarioRepository;
import com.compass.thiagofv.securityJwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepository repository;

    public ResponseEntity signin(CredencialVO data){
        try{
            var username = data.getNomeUsuario();
            var password = data.getSenha();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenVO();

            if(user != null){
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
            }else{
                throw new UsernameNotFoundException("Usuario " + username + " não encontrado");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e){
            throw new BadCredentialsException("Usuario/senha inválido");
        }
    }

    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);

        var tokenResponse = new TokenVO();
        if (user != null) {
            tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
