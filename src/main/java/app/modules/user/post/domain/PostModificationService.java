package app.modules.user.post.domain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
class PostModificationService {

    private PostModificationPort postModificationPort;

    public void savePost(Post post) throws Exception {
        postModificationPort.savePost(post);
    }
}
