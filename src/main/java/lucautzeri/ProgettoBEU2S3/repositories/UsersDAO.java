package lucautzeri.ProgettoBEU2S3.repositories;

import lucautzeri.ProgettoBEU2S3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
