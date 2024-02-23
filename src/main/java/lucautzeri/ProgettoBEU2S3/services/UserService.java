package lucautzeri.ProgettoBEU2S3.services;

import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.exceptions.BadRequestException;
import lucautzeri.ProgettoBEU2S3.exceptions.NotFoundException;
import lucautzeri.ProgettoBEU2S3.payloads.RegisterDTO;
import lucautzeri.ProgettoBEU2S3.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    public User saveUser(RegisterDTO payload){
        //Controlla se l'email è gia in uso, se lo è, dà un errore
        usersDAO.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già stata registrata");
        });
        // Creazione user
        User newUser = new User(payload.name(),
                payload.surname(),
                payload.email(),
                payload.username(),
                payload.password());
        // Salvataggio nuovo utente
        return usersDAO.save(newUser);
    }

    public User findByEmail(String email){
        return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("The e-mail " + email + " was not found!"));
    }

}
