package lucautzeri.ProgettoBEU2S3.controllers;

import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.payloads.RegisterDTO;
import lucautzeri.ProgettoBEU2S3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody RegisterDTO newRegister){
        return this.userService.saveUser(newRegister);
    }
}
