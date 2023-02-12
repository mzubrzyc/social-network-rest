package app.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPostsDto {
    private Long userId;
    private String login;
    private String name;
    private String surname;
    private String nick;
    private LocalDate creationDate;
    private List<PostDto> userPosts;

}
