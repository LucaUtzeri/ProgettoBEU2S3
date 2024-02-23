package lucautzeri.ProgettoBEU2S3.security.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    // Creazione Nuovo Token
    public String newToken(User user) {
        //Metodo creazione token -> necessita Header (Automatico), Payload e Firma
        //Creazione Payload:
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis())) //Data creazione
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Scadenza, calcolata in millisecondi -> equivale a un giorno
                .subject(String.valueOf(user.getUserId())) // Il proprietario del token
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firma
                .compact();
    }

    // Validazione Token
    public void checkToken(String token){
        // Questo lancerà eccezioni in base al token se invalido o scaduto.
        // Fattibile con un Try/Catch
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception error){
            throw new UnauthorizedException("Invalid token!");
        }
    }

    public String getIdFromToken(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        // Il Subject sara l' UUID dell'utente in questo caso

    }
}
