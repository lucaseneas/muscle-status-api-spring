package com.example.muscle_status_api.infra.security;

import com.example.muscle_status_api.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Executa essa classe antes de executar a classe do SecurityConfiguration
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        System.out.println(token);
        //faz a verificação e salva o usuario no contexto
        if(token != null){
            var email = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(email);
            System.out.println("User: "+user+" Email: "+email);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //se der tudo certo passa para a proxima etapa do filtro
        filterChain.doFilter(request,response);
    }

    //Cria o header e verifica se existe algum token, se não existir retorna null, se existir ele recorta a escrita Bearer do token e salva
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ","");
    }

}
