package lucautzeri.ProgettoBEU2S3.security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.exceptions.UnauthorizedException;
import lucautzeri.ProgettoBEU2S3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    // Check Header
        String header = request.getHeader("Authorization"); //Bearer [Token]
        if (header == null) throw new UnauthorizedException("Missing Token!");
        // Accesso al token rimuovendo "Bearer"
        String accessToken = header.substring(7);
        // Importo metodo verifica token + eccezioni
        jwtTools.checkToken(accessToken);
        // Se risulta, parte la catena per arrivare all'endpoint
        // 1: Estrazione ID utente dal DB
        String id = jwtTools.getIdFromToken(accessToken);
        User user = userService.findById(UUID.fromString(id));
        // 2: Comunicare a Spring Security l'autentificazione
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    // Rimuovere il filtro a Login e Register
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
        // "Se il path = /auth + /[w/e], non usare il filtro"

    }

}
