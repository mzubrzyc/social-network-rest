package app.modules.user.post.infrastructure;

import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostModificationPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JpaPostModificationAdapter implements PostModificationPort {

    JpaPostRepository jpaPostRepository;

    @Override
    public void savePost(Post post) throws Exception {
        jpaPostRepository.saveAndFlush(PostEntityMapper.toPostEntity(post));
    }

    @Override
    public void deletePost(Post post) {

    }
}
