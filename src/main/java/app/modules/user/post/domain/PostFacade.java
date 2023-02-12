package app.modules.user.post.domain;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostFacade {

    private final PostRetrievingService postRetrievingService;
    private final PostModificationService postModificationService;

    public List<Post> getAllPosts() {
        return postRetrievingService.getAllPosts();
    }

    public Optional<Post> getPostById(long postId) {
        return postRetrievingService.getPostById(postId);
    }

    public void savePost(Post post) throws Exception {
        if (post.getPostId() == null) {
            postModificationService.savePost(post);
        }
    }

    public List<Post> getMostViewedPostsLimitTo(int numberOfRows) {
        return postRetrievingService.getMostViewedPostsLimitTo(numberOfRows);
    }

}
