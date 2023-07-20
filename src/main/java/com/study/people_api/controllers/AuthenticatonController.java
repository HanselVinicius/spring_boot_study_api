package com.study.people_api.controllers;


import com.study.people_api.entitiy.users.AuthenticationDTO;
import com.study.people_api.entitiy.users.UserRepository;
import com.study.people_api.entitiy.users.Usuario;
import com.study.people_api.infra.security.TokenJWTDTO;
import com.study.people_api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthenticatonController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthenticationDTO data){
        var token = new UsernamePasswordAuthenticationToken(data.login(),data.senha());
        var authentication = manager.authenticate(token);
        var jwtToken = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDTO(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthenticationDTO data){
        if (repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        var senha = new BCryptPasswordEncoder().encode(data.senha());
        var user = new Usuario(data.login(),senha);
        repository.save(user);
        return ResponseEntity.ok().build();
    }




}
