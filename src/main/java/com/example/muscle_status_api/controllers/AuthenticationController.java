package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.user.User;
import com.example.muscle_status_api.domain.user.UserRepository;
import com.example.muscle_status_api.dto.AuthenticationDTO;
import com.example.muscle_status_api.dto.LoginResponseDTO;
import com.example.muscle_status_api.dto.RegisterDTO;
import com.example.muscle_status_api.infra.security.TokenService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    //Vem do infra/security/SecurityConfiguration
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        //faz a autenticação do usuario atraves dos metodos
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity regsiter(@RequestBody @Valid RegisterDTO data){

        //verifica se ja existe alguem com mesmo email cadastrado
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        //Criptografa a senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        //Cria um usuario e manda a para o db
        User newUser = new User(data.name(),data.email(),encryptedPassword,data.role());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
