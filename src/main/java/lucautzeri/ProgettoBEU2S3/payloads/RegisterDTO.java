package lucautzeri.ProgettoBEU2S3.payloads;

public record RegisterDTO(String name,
                          String surname,
                          String username,
                          String email,
                          String password) {
}
