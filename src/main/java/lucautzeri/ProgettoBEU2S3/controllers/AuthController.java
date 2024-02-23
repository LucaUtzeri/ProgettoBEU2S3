package lucautzeri.ProgettoBEU2S3.controllers;

import lucautzeri.ProgettoBEU2S3.payloads.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public void login(@RequestBody LoginDTO payload){
        // Logica da metter nel service:
        // Check credenziali
        // Se OK -> Genera token, ritorna token
        // Se NO -> Exception Error (401)
    }
}
