package app.fixture.post;

import app.modules.user.post.infrastructure.PostJpaRepository;
import app.modules.user.post.infrastructure.PostEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class JpaPostFixture {

    @Autowired
    PostJpaRepository postJpaRepository;

    public List<PostEntity> savePosts(List<PostEntity> postEntityList) {
        return postJpaRepository.saveAllAndFlush(postEntityList);
    }

}
