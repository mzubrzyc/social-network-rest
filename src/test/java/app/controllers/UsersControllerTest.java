package app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import app.dto.UserDto;
import app.fixter.post.JpaPostFixture;
import app.fixter.post.PostGenerator;
import app.fixter.user.JpaUserFixture;
import app.fixter.user.UserGenerator;
import app.modules.user.User;
import app.modules.user.UserFacade;
import app.modules.user.infrastructure.UserEntity;
import app.modules.user.infrastructure.UserEntityMapper;
import app.modules.user.post.domain.Post;
import app.modules.user.post.domain.PostFacade;
import app.modules.user.post.infrastructure.PostEntity;
import app.modules.user.post.infrastructure.PostEntityMapper;
import app.testconfig.IntegrationTest;
import app.utils.TableEraser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@IntegrationTest
class UsersControllerTest {

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
    @Autowired
    UserFacade userFacade;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void afterEachActions() {
        tableEraser.deleteFromTables();
    }

    @Test
    @SneakyThrows
    @DisplayName(("Endpoint '/users/delete/1' should delete user of id 1 and his posts"))
    void deleteUser() {
        // given: random 11 users, random posts
        RestTemplate restTemplate = new RestTemplate();
        String baseUrlWithPort = BASE_URL + serverPort;
        List<User> generatedUsers = UserGenerator.generateUsers(11);
        List<UserEntity> userEntities = jpaUserFixture.saveUsers(UserEntityMapper.toUserEntity(generatedUsers));
        List<Post> generatedPosts = PostGenerator.generatePostsForUsers(userEntities);
        List<PostEntity> postEntities = jpaPostFixture.savePosts(PostEntityMapper.toPostEntity(generatedPosts));
        long userIdToBeDeleted = userEntities.get(0).getUserId();
        long postsOfUser1 = postEntities.stream()
                                        .filter(post -> post.getUserId() == userIdToBeDeleted)
                                        .count();
        // when:
        restTemplate.delete(baseUrlWithPort + "/users/delete/" + userIdToBeDeleted);
        // then:
        assertThat(userFacade.getAllUsers()).hasSize(userEntities.size() - 1);
        assertThat(postFacade.getAllPosts()).hasSize(postEntities.size() - (int) postsOfUser1);
    }

    @Test
    @SneakyThrows
    @DisplayName("Endpoint '/users/save/{someUserJson}' should save someUser into database")
    void saveUser() {
        // given: "User to be saved"
        final String SAVE_URL = BASE_URL + serverPort + "/users/save";
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto(
            "loginUserOne",
            "nameUserOne",
            "surnameUsrOne",
            "nickUserOne"
        );
        String userDtoJson = objectMapper.writeValueAsString(userDto);
        // when:
        mockMvc.perform(
                   post(SAVE_URL)
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .content(userDtoJson)
               )
               .andExpect(MockMvcResultMatchers.status().isOk());
        // then:
        assertThat(userFacade.getUserByLogin("loginUserOne")).isPresent();
    }

    @Test
    @SneakyThrows
    @DisplayName("Endpoint '/users/update/{someUserJson}' should update someUser in database")
    void updateUser() {
        // given: "User to be saved"
        final String UPDATE_URL = BASE_URL + serverPort + "/users/update";
        ObjectMapper objectMapper = new ObjectMapper();
        UserEntity userEntity = new UserEntity(
            "loginUserOne",
            "nameUserOne",
            "surnameUsrOne",
            "nickUserOne",
            LocalDate.now()
        );
        UserEntity userEntitySaved = jpaUserFixture.saveUser(userEntity);
        UserDto userOneUpdate = new UserDto(
            userEntitySaved.getUserId(),
            userEntitySaved.getLogin(),
            userEntitySaved.getName() + "Update",
            userEntitySaved.getSurname() + "Update",
            userEntitySaved.getNick() + "Update"
        );
        String userDtoUpdateJson = objectMapper.writeValueAsString(userOneUpdate);
        // when:
        mockMvc.perform(
                   put(UPDATE_URL)
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .content(userDtoUpdateJson)
               )
               .andExpect(MockMvcResultMatchers.status().isOk());
        // then:
        User userOneUpdated = userFacade.getUserByLogin("loginUserOne").orElse(null);
        assertThat(userOneUpdated)
            .returns(userOneUpdate.getName(), from(User::getName))
            .returns(userOneUpdate.getSurname(), from(User::getSurname))
            .returns(userOneUpdate.getNick(), from(User::getNick));
    }

}