package app.modules.user;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class User {
    private Long userId;
    private String login;
    private String name;
    private String surname;
    private String nick;
    @Setter
    private LocalDate creationDate;
    private List<UserPost> userPosts;

    public User(Long userId,
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
             LocalDate.now(),
             Collections.emptyList());
    }

    public User(String login,
                String name,
                String surname,
                String nick
    ) {
        this(null,
             login,
             name,
             surname,
             nick,
             LocalDate.now(),
             Collections.emptyList());
    }
}
