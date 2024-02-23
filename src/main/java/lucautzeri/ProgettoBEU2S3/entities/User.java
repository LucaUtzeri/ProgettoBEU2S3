package lucautzeri.ProgettoBEU2S3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID userId;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

    public User(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
