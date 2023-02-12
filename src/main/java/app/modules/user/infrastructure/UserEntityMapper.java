package app.modules.user.infrastructure;

import app.modules.user.User;
import app.modules.user.UserPost;
import app.modules.user.post.infrastructure.PostEntity;
import java.util.Collections;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntityMapper {

    public static List<User> fromUserEntity(List<UserEntity> userEntityList) {
        return userEntityList
            .stream()
            .map(entity ->
                     new User(
                         entity.getUserId(),
                         entity.getLogin(),
                         entity.getName(),
                         entity.getSurname(),
                         entity.getNick(),
                         entity.getCreationDate(),
                         Collections.emptyList()
                     )
            )
            .toList();
    }

    public static User fromUserEntity(UserEntity userEntity) {
        return new User(
            userEntity.getUserId(),
            userEntity.getLogin(),
            userEntity.getName(),
            userEntity.getSurname(),
            userEntity.getNick(),
            userEntity.getCreationDate(),
            Collections.emptyList()
        );
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
            user.getLogin(),
            user.getName(),
            user.getSurname(),
            user.getNick(),
            user.getCreationDate()
        );
    }

    public static List<UserEntity> toUserEntity(List<User> userList) {
        return userList.stream()
                       .map(user -> new UserEntity(user.getLogin(),
                                                   user.getName(),
                                                   user.getSurname(),
                                                   user.getNick(),
                                                   user.getCreationDate())
                       )
                       .toList();
    }

    private List<UserPost> toUserPost(List<PostEntity> postEntityList) {
        return postEntityList
            .stream()
            .map(entity -> new UserPost(
                     entity.getPostId(),
                     entity.getUserId(),
                     entity.getContent(),
                     entity.getPostViews(),
                     entity.getCreationDate()
                 )
            )
            .toList();
    }

}
