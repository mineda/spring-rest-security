package br.gov.sp.fatec.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.security.JwtUtils;
import br.gov.sp.fatec.security.Login;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LoginController {
    
    @Autowired
    private AuthenticationManager auth;
    
    @Autowired
    private UserDetailsService segurancaService;

    public void setAuth(AuthenticationManager auth) {
        this.auth = auth;
    }
    
    public void setSegurancaService(UserDetailsService segurancaService) {
    	this.segurancaService = segurancaService;
    }
    
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @CrossOrigin(exposedHeaders = "Token")
    public ResponseEntity<Usuario> login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {
        Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Usuario usuario = (Usuario) auth.authenticate(credentials).getPrincipal();
        response.setHeader("Token", JwtUtils.generateToken(usuario));
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }


}
