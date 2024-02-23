package lucautzeri.ProgettoBEU2S3.services;

import lucautzeri.ProgettoBEU2S3.payloads.RegisterDTO;
import lucautzeri.ProgettoBEU2S3.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    public saveUser(RegisterDTO payload){
        usersDAO.findByEmail(payload.email);
    }
}
