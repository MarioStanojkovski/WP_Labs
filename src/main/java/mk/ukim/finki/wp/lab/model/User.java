package mk.ukim.finki.wp.lab.model;import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
}
