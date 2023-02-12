package app.modules.user;

import static org.assertj.core.api.Assertions.assertThat;

import app.dto.UserLoginPostContentDto;
import app.fixter.user.JpaUserFixture;
import app.fixter.user.UserGenerator;
import app.modules.user.infrastructure.UserEntity;
import app.modules.user.infrastructure.UserEntityMapper;
import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostFacade;
import app.orchestrators.SavingPostsOrchestrator;
import app.testconfig.IntegrationTest;
import app.utils.TableEraser;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@IntegrationTest
class UserFacadeTest {

    @Autowired
    UserFacade userFacade;
    @Autowired
    TableEraser tableEraser;
    @Autowired
    JpaUserFixture jpaUserFixture;
    @Autowired
    PostFacade postFacade;
    @Autowired
    SavingPostsOrchestrator savingPostsOrchestrator;

    @AfterEach
    void afterEachActions() {
        tableEraser.deleteFromTables();
    }

    @Test
    @SneakyThrows
    @DisplayName("User exists in db after being saved")
    void saveNewUser() {
        // given:
        User userOne = new User("userOneLogin",
                                "userOneName",
                                "userOneSurname",
                                "userOneNick");
        // when:
        userFacade.saveNewUser(userOne);
        // then:
        List<User> allUsers = userFacade.getAllUsers();
        assertThat(allUsers).hasSize(1);
    }

    @Test
    @SneakyThrows
    @DisplayName("Deleting user from db do the same for his posts")
    void deleteUser() {
        // given:
        User userOne = new User("userOneLogin",
                                "userOneName",
                                "userOneSurname",
                                "userOneNick");
        UserEntity userOneEntity = jpaUserFixture.saveUser(UserEntityMapper.toUserEntity(userOne));
        UserLoginPostContentDto userOnePostDto = new UserLoginPostContentDto(userOne.getLogin(),
                                                                             "userOnePostContent");
        savingPostsOrchestrator.savePostForUser(userOnePostDto);
        // when:
        userFacade.deleteUser(UserEntityMapper.fromUserEntity(userOneEntity));
        // then:
        Optional<User> userByLogin = userFacade.getUserByLogin(userOne.getLogin());
        assertThat(userByLogin).isEmpty();
        List<Post> allPosts = postFacade.getAllPosts();
        assertThat(allPosts).isEmpty();
    }

    @Test
    @SneakyThrows
    @DisplayName("Retrieve 11 users by user ids")
    void getUsersByIds() {
        // given:
        List<User> generatedUsers = UserGenerator.generateUsers(11);
        List<UserEntity> userEntities = jpaUserFixture.saveUsers(UserEntityMapper.toUserEntity(generatedUsers));
        // when:
        List<User> usersByIds = userFacade.getUsersByIds(userEntities.stream().map(UserEntity::getUserId).toList());
        // then:
        assertThat(usersByIds).hasSameSizeAs(userEntities);
    }

}