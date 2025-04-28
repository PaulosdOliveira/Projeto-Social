package com.github.PaulosdOliveira.social.security.filter;

import com.github.PaulosdOliveira.social.application.usuario.UsuarioService;
import com.github.PaulosdOliveira.social.jwt.JwtService;
import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null) {
            String email = jwtService.getEmailByToken(token);
            Usuario usuario = usuarioService.findByEmail(email);
            if (usuario != null) {
                UserDetails userDetails = User.withUsername(usuario.getEmail())
                        .password(usuario.getSenha())
                        .build();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, usuario.getId(), userDetails.getAuthorities()));
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        return uri.contains("/usuario");
    }

    public String getToken(HttpServletRequest request) throws ServletException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String[] tokenDividido = token.split(" ");
            return tokenDividido[1];
        }
        return null;
    }


}
