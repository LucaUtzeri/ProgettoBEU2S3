package lucautzeri.ProgettoBEU2S3.controllers;

import lucautzeri.ProgettoBEU2S3.payloads.LoginDTO;
import lucautzeri.ProgettoBEU2S3.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO payload){
        return authService.checkUserThenCreateToken(payload);
    }
}
