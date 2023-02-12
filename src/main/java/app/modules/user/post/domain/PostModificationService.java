package app.modules.user.post.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class PostModificationService {

    private PostModificationPort postModificationPort;

    public void savePost(Post post) throws Exception {
        postModificationPort.savePost(post);
    }
}
