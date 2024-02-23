package lucautzeri.ProgettoBEU2S3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue
    private UUID userId;
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
