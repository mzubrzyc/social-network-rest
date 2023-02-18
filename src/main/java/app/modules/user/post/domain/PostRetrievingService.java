package app.modules.user.post.domain;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
class PostRetrievingService {

    private final PostRetrievingPort postRetrievingPort;

    public List<Post> getAllPosts() {
        log.info("Getting all posts");
        return postRetrievingPort.getAllPosts();
    }

    public Optional<Post> getPostById(long postId) {
        log.info("Getting post for id {}" , postId);
        return postRetrievingPort.getPostById(postId);
    }

    public List<Post> getMostViewedPostsLimitTo(int numberOfResults) {
        log.info("Getting {} most viewed posts ", numberOfResults);
        return postRetrievingPort.getMostViewedPostsLimitTo(numberOfResults);
    }
}
