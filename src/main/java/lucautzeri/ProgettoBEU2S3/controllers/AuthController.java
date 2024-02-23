package lucautzeri.ProgettoBEU2S3.controllers;

import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.payloads.LoginDTO;
import lucautzeri.ProgettoBEU2S3.payloads.LoginResponse;
import lucautzeri.ProgettoBEU2S3.payloads.RegisterDTO;
import lucautzeri.ProgettoBEU2S3.services.AuthService;
import lucautzeri.ProgettoBEU2S3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDTO payload){
        return new LoginResponse(authService.checkUserThenCreateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody RegisterDTO newRegister) {
        return this.userService.saveUser(newRegister);
    }
}
