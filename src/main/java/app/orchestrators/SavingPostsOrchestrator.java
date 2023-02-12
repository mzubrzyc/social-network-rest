package app.orchestrators;

import app.dto.UserLoginPostContentDto;
import app.modules.user.User;
import app.modules.user.UserFacade;
import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostFacade;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SavingPostsOrchestrator {

    private final UserFacade userFacade;
    private final PostFacade postFacade;

    public void savePostForUser(UserLoginPostContentDto userLoginPostContentDto) throws Exception {
        User userByLogin =
            userFacade.getUserByLogin(userLoginPostContentDto.login())
                      .orElseThrow(() -> new Exception("User for login: " + userLoginPostContentDto.login() + " does not exists"));
        postFacade.savePost(new Post(userByLogin.getUserId(),
                                     userLoginPostContentDto.content(),
                                     LocalDateTime.now())
        );
    }

}
