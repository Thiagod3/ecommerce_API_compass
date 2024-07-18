package com.compass.thiagofv.web;

import com.compass.thiagofv.domain.Usuario;
import com.compass.thiagofv.dto.AuthDTO;
import com.compass.thiagofv.dto.LoginResponseDTO;
import com.compass.thiagofv.dto.RegisterDTO;
import com.compass.thiagofv.infra.security.TokenService;
import com.compass.thiagofv.repositories.UsuarioRepository;
import com.compass.thiagofv.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null || this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), data.email(), encryptedPassword);
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reset-senha/solicitar")
    public ResponseEntity solicitarResetSenha(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario usuario = usuarioRepo.findByLogin(userDetails.getUsername());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        String resetToken = tokenService.generatePasswordResetToken(usuario);
        emailService.sendPasswordResetEmail(usuario.getEmail(), resetToken);
        return ResponseEntity.ok("Token de redefinição de senha enviado para o email.");
    }

    @PostMapping("/reset-senha/resetar")
    public ResponseEntity resetarSenha(@RequestHeader("Authorization") String authHeader, @RequestBody String novaSenha) {
        String resetToken = authHeader.substring(7);
        Usuario usuario = usuarioRepo.findByLogin(tokenService.validateToken(resetToken));
        String email = usuario.getEmail();

        if (email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado.");
        }

        usuario.setPassword(new BCryptPasswordEncoder().encode(novaSenha));
        repository.save(usuario);
        return ResponseEntity.ok("Senha atualizada com sucesso.");
    }
}
