package app.modules.user.post.infrastructure;

import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostRetrievingPort;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class JpaPostRetrievingAdapter implements PostRetrievingPort {

    JpaPostRepository jpaPostRepository;

    @Override
    public List<Post> getAllPosts() {
        return PostEntityMapper.fromPostEntity(jpaPostRepository.findAll());
    }

    @Override
    public Optional<Post> getPostById(long postId) {
        Optional<PostEntity> optionalPost = jpaPostRepository.findById(postId);
        return optionalPost.map(PostEntityMapper::fromPostEntity);
    }

    @Override
    public List<Post> getMostViewedPostsLimitTo(int numberOfResults) {
        Pageable pageable = PageRequest.of(0, numberOfResults);
        return PostEntityMapper.fromPostEntity(jpaPostRepository.findPostsWithMostViewsLimitedTo(pageable));
    }
}
