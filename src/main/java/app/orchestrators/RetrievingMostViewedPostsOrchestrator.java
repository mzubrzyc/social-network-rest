package app.orchestrators;

import app.controllers.utils.ControllerDtoMapper;
import app.dto.PostDto;
import app.modules.user.User;
import app.modules.user.UserFacade;
import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostFacade;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RetrievingMostViewedPostsOrchestrator {

    private final PostFacade postFacade;
    private final UserFacade userFacade;

    public List<PostDto> getMostViedPostsLimitTo(int numberOfRows) {
        List<Post> posts = postFacade.getMostViewedPostsLimitTo(numberOfRows);
        List<User> users = userFacade.getUsersByIds(getDistinctUserIds(posts));
        return ControllerDtoMapper.toPostDto(posts, users);
    }

    private List<Long> getDistinctUserIds(List<Post> posts) {
        return posts.stream()
                    .map(Post::getUserId)
                    .distinct()
                    .toList();
    }

}
