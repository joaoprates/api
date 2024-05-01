package prates.desenvolvimento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import prates.desenvolvimento.api.domain.usuario.DadosAutenticacao;
import prates.desenvolvimento.api.domain.usuario.Usuario;
import prates.desenvolvimento.api.infra.security.DadosTokenJWT;
import prates.desenvolvimento.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
    
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
    
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    
}
