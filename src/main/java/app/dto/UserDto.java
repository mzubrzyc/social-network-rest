package app.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String login;
    private String name;
    private String surname;
    private String nick;
    private LocalDate creationDate;
}
