package app.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import app.dto.MostViewedPostsDto;
import app.fixter.post.JpaPostFixture;
import app.fixter.post.PostGenerator;
import app.fixter.user.JpaUserFixture;
import app.fixter.user.UserGenerator;
import app.modules.user.User;
import app.modules.user.infrastructure.UserEntity;
import app.modules.user.infrastructure.UserEntityMapper;
import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostFacade;
import app.modules.user.post.infrastructure.PostEntity;
import app.modules.user.post.infrastructure.PostEntityMapper;
import app.testconfig.IntegrationTest;
import app.utils.TableEraser;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@IntegrationTest
class PostsControllerTest {

    final static String BASE_URL = "http://localhost:";
    @LocalServerPort
    int serverPort;
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
    @SneakyThrows
    @DisplayName("Endpoint '/posts/rank/max/10' should return 10 max viewed posts")
    void getTenPostsWithHighestViewCount() {
        // given: random 11 users, random posts
        RestTemplate restTemplate = new RestTemplate();
        String baseUrlWithPort = BASE_URL + serverPort;
        List<User> generatedUsers = UserGenerator.generateUsers(11);
        List<UserEntity> userEntities = jpaUserFixture.saveUsers(UserEntityMapper.toUserEntity(generatedUsers));
        List<Post> generatedPosts = PostGenerator.generatePostsForUsers(userEntities);
        jpaPostFixture.savePosts(PostEntityMapper.toPostEntity(generatedPosts));
        // when:
        ResponseEntity<MostViewedPostsDto> responseEntity = restTemplate.getForEntity(baseUrlWithPort + "/posts/rank/max/10", MostViewedPostsDto.class);
        // then:
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        MostViewedPostsDto body = responseEntity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMostViewedPostsList()).hasSize(10);
    }
}