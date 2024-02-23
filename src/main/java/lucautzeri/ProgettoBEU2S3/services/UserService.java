package lucautzeri.ProgettoBEU2S3.services;

import lucautzeri.ProgettoBEU2S3.entities.User;
import lucautzeri.ProgettoBEU2S3.exceptions.BadRequestException;
import lucautzeri.ProgettoBEU2S3.exceptions.NotFoundException;
import lucautzeri.ProgettoBEU2S3.payloads.RegisterDTO;
import lucautzeri.ProgettoBEU2S3.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    public User saveUser(RegisterDTO payload){
        //Controlla se l'email è gia in uso, se lo è, dà un errore
        usersDAO.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("The e-mail " + user.getEmail() + " already exists!");
        });
        // Creazione user
        User newUser = new User(
                payload.name(),
                payload.surname(),
                payload.username(),
                payload.email(),
                payload.password());
        // Salvataggio nuovo utente
        return usersDAO.save(newUser);
    }

    public User findByEmail(String email){
        return usersDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        "The e-mail " + email + " was not found!"));
    }

    public User findById(UUID id){
        return usersDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found."));
    }
}
