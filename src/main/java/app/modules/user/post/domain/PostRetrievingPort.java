package app.modules.user.post.domain;

import java.util.List;
import java.util.Optional;

public interface PostRetrievingPort {

    List<Post> getAllPosts();

    Optional<Post> getPostById(long postId);

    List<Post> getMostViewedPostsLimitTo(int numberOfResults);

}
