package app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private LocalDate creationDate;

    public UserDto(
        Long userId,
        String login,
        String name,
        String surname,
        String nick
    ) {
        this(userId,
             login,
             name,
             surname,
             nick,
             null
        );
    }

    public UserDto(
        String login,
        String name,
        String surname,
        String nick
    ) {
        this(null,
             login,
             name,
             surname,
             nick,
             null
        );
    }


}
