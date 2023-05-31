package app.modules.user.post.infrastructure;

import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostModificationPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostModificationAdapter implements PostModificationPort {

    PostJpaRepository postJpaRepository;

    @Override
    public void savePost(Post post) throws Exception {
        postJpaRepository.saveAndFlush(PostEntityMapper.toPostEntity(post));
    }

    @Override
    public void deletePost(Post post) {

    }
}
