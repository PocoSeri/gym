package com.gym.gym.config;

import com.gym.gym.exception.AppException;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter("/api/**") // Specifica il percorso delle richieste da filtrare
public class JwtFilter implements Filter {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String header = httpRequest.getHeader(jwtConfig.getHeader());
        String requestURI = httpRequest.getRequestURI();
        if (requestURI.startsWith("/api/users/")) {
            chain.doFilter(request, response);
            return;
        }
        //todo: aggiungere controllo per role al signup
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            throw new AppException(AppException.ErrCode.BAD_INPUT, "Jwt not valid");
        }

        String token = header.replace(jwtConfig.getPrefix(), "");

        try {
            // Verifica la validità del token JWT
            Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token);

            // Se il token è valido, permetti alla richiesta di proseguire
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            // Gestisci l'eccezione in base alle tue esigenze
            // Ad esempio, restituisci una risposta di errore
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro
    }

    @Override
    public void destroy() {
        // Distruggi il filtro
    }
}
