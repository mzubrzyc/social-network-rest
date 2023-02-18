package app.modules.user.post.domain;

import static org.assertj.core.api.Assertions.assertThat;

import app.fixter.post.JpaPostFixture;
import app.fixter.post.PostGenerator;
import app.fixter.user.JpaUserFixture;
import app.fixter.user.UserGenerator;
import app.modules.user.User;
import app.modules.user.infrastructure.UserEntity;
import app.modules.user.infrastructure.UserEntityMapper;
import app.modules.user.post.infrastructure.PostEntity;
import app.modules.user.post.infrastructure.PostEntityMapper;
import app.testconfig.IntegrationTest;
import app.utils.TableEraser;
import java.util.Comparator;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@IntegrationTest
class PostFacadeTest {

    @Autowired
    PostFacade postFacade;
    @Autowired
    TableEraser tableEraser;
    @Autowired
    JpaUserFixture jpaUserFixture;
    @Autowired
    JpaPostFixture jpaPostFixture;

    @AfterEach
    void afterEachActions() {
        tableEraser.deleteFromTables();
    }

    @Test
    @DisplayName("Displaying 10 post with most views")
    @SneakyThrows
    void getMostViewedPostsLimitResultsTo() {
        // given: random 11 users, random posts
        List<User> generatedUsers = UserGenerator.generateUsers(1100);
        List<UserEntity> userEntities = jpaUserFixture.saveUsers(UserEntityMapper.toUserEntity(generatedUsers));
        List<Post> generatedPosts = PostGenerator.generatePostsForUsers(userEntities);
        List<PostEntity> postEntitiesGeneratedFromPosts = PostEntityMapper.toPostEntity(generatedPosts);
        List<PostEntity> postEntitiesSaved = jpaPostFixture.savePosts(PostEntityMapper.toPostEntity(generatedPosts));
        // when:
        List<Post> mostViewedPostsFromDb = postFacade.getMostViewedPostsLimitTo(10);
        // then:
        List<PostEntity> tenMostViedPosts = postEntitiesSaved.stream()
                                                             .sorted(
                                                                 Comparator.comparing(PostEntity::getPostViews)
                                                                           .reversed()
                                                                           .thenComparing(PostEntity::getPostId))
                                                             .limit(10)
                                                             .toList();
        assertThat(mostViewedPostsFromDb)
            .extracting(
                Post::getPostId,
                Post::getPostViews,
                Post::getContent,
                Post::getCreationTimestamp,
                Post::getUserId)
            .containsAll(
                tenMostViedPosts
                    .stream()
                    .map(post -> Tuple.tuple(
                        post.getPostId(),
                        post.getPostViews(),
                        post.getContent(),
                        post.getCreationDate(),
                        post.getUserId())
                    )
                    .toList()
            );
    }

}