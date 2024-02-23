package lucautzeri.ProgettoBEU2S3.services;

import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.exceptions.UnauthorizedException;
import lucautzeri.ProgettoBEU2S3.payloads.LoginDTO;
import lucautzeri.ProgettoBEU2S3.security.JWT.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
        @Autowired
        private UserService userService;

        @Autowired
        private JWTTools jwtTools;

        // Logica da metter nel service:
        // Check credenziali
        // Se OK -> Genera token, ritorna token
        // Se NO -> Exception Error (401)

        public String checkUserThenCreateToken(LoginDTO payload){
            User user = userService.findByEmail(payload.email());
            if ( (user.getPassword().equals(payload.password()))){
                return jwtTools.newToken(user);
            } else {
                throw new UnauthorizedException("Unauthorized Access");
            }
        }
    }
