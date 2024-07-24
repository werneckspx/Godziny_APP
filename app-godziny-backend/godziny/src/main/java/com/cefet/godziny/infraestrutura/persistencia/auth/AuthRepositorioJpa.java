package com.cefet.godziny.infraestrutura.persistencia.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import com.cefet.godziny.api.auth.AuthDto;
import com.cefet.godziny.api.auth.AuthResponseDto;
import com.cefet.godziny.domain.porta.auth.IAuthRepositorio;
import com.cefet.godziny.infraestrutura.auth.JwtService;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;

@Component
public class AuthRepositorioJpa implements IAuthRepositorio{

    @Autowired
    private UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Autowired
    private JwtService jwtService;

    @Autowired 
    private AuthenticationManager authenticationManager;

    public AuthRepositorioJpa(UsuarioRepositorioJpa usuarioRepositorioJpa, AuthenticationManager authenticationManager, JwtService jwtService){
        this.usuarioRepositorioJpa= usuarioRepositorioJpa;
        this.authenticationManager  = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponseDto loginUsuario(AuthDto dto) {
       authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
       );
        UsuarioEntidade usuarioEntidade = usuarioRepositorioJpa.findByEmail(dto.getEmail());
        var jwtToken = jwtService.generateToken(usuarioEntidade);
        return AuthResponseDto.builder()
        .token(jwtToken)
        .build();
    }
    
}
