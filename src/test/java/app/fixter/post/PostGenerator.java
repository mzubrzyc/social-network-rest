package app.fixter.post;

import app.modules.user.infrastructure.UserEntity;
import app.modules.user.post.domain.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class PostGenerator {

    private final int MAX_NO_OF_POSTS_PER_USER = 5;
    private final int MAX_NO_OF_CHARS_PER_POST = 100;
    private final int MAX_NO_OF_VIEWS = 100;
    Random random = new Random();

    public List<Post> generatePostsForUsers(List<UserEntity> users) {
        return users
            .stream()
            .flatMap(user ->
                         Stream.generate(() -> generatePostForUserId(user.getUserId()))
                               .limit(random.nextInt(1, MAX_NO_OF_POSTS_PER_USER)))
            .toList();
    }

    private Post generatePostForUserId(long userId) {
        return new Post(userId,
                        RandomStringUtils.randomAlphabetic(random.nextInt(5, MAX_NO_OF_CHARS_PER_POST)),
                        random.nextLong(1, MAX_NO_OF_VIEWS),
                        LocalDateTime.now());
    }

}
