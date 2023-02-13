package app.controllers.utils;

import app.dto.PostDto;
import app.dto.UserDto;
import app.modules.user.User;
import app.modules.user.post.domain.Post;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerDtoMapper {

    public static List<UserDto> toUserDto(List<User> userList) {
        return userList.stream()
                       .map(user -> new UserDto(user.getUserId(),
                                                user.getLogin(),
                                                user.getName(),
                                                user.getSurname(),
                                                user.getNick(),
                                                user.getCreationDate())
                       )
                       .toList();
    }

    public static List<PostDto> toPostDto(List<Post> postList, List<User> userList) {
        Map<Long, UserDto> userByUserIdMap =
            toUserDto(userList)
                .stream()
                .collect(Collectors.toMap(UserDto::getUserId, Function.identity()));
        return postList.stream()
                       .map(post -> new PostDto(post.getPostId(),
                                                userByUserIdMap.get(post.getUserId()),
                                                post.getContent(),
                                                post.getPostViews(),
                                                post.getCreationTimestamp())
                       )
                       .toList();
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getUserId(),
                           user.getLogin(),
                           user.getName(),
                           user.getSurname(),
                           user.getNick(),
                           user.getCreationDate()
        );
    }

    public static User toUsr(UserDto userDto) {
        return new User(userDto.getUserId(),
                        userDto.getLogin(),
                        userDto.getName(),
                        userDto.getSurname(),
                        userDto.getNick()
        );
    }
}
