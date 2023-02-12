package app.modules.user.post.domain;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class PostRetrievingService {

    private final PostRetrievingPort postRetrievingPort;

    public List<Post> getAllPosts() {
        return postRetrievingPort.getAllPosts();
    }

    public Optional<Post> getPostById(long postId) {
        return postRetrievingPort.getPostById(postId);
    }

    public List<Post> getMostViewedPostsLimitTo(int numberOfResults) {
        return postRetrievingPort.getMostViewedPostsLimitTo(numberOfResults);
    }
}
