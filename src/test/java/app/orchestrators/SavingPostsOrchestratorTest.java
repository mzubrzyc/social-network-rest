package app.orchestrators;

import static org.assertj.core.api.Assertions.assertThat;

import app.dto.UserLoginPostContentDto;
import app.fixter.user.JpaUserFixture;
import app.modules.user.User;
import app.modules.user.infrastructure.UserEntityMapper;
import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostFacade;
import app.testconfig.IntegrationTest;
import app.utils.TableEraser;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
class SavingPostsOrchestratorTest {
    @Autowired
    PostFacade postFacade;
    @Autowired
    TableEraser tableEraser;
    @Autowired
    JpaUserFixture jpaUserFixture;
    @Autowired
    SavingPostsOrchestrator savingPostsOrchestrator;

    @AfterEach
    void afterEachActions() {
        tableEraser.deleteFromTables();
    }

    @Test
    @DisplayName("User exists in db after being saved")
    @SneakyThrows
    void savePostForUser() {
        // given:
        User userOne = new User("userOneLogin",
                                "userOneName",
                                "userOneSurname",
                                "userOneNick");
        jpaUserFixture.saveUser(UserEntityMapper.toUserEntity(userOne));
        UserLoginPostContentDto userOnePostDto = new UserLoginPostContentDto(userOne.getLogin(),
                                                                             "userOnePostContent");
        // when:
        savingPostsOrchestrator.savePostForUser(userOnePostDto);
        // then:
        List<Post> allPosts = postFacade.getAllPosts();
        assertThat(allPosts).hasSize(1);
    }

}