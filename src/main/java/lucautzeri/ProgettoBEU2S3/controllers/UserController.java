package lucautzeri.ProgettoBEU2S3.controllers;

import lucautzeri.ProgettoBEU2S3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserService userService;

}
