package app.modules.user;

import java.util.List;
import java.util.Optional;

public interface UserRetrievingPort {

    List<User> getAllUsers();

    Optional<User> getUserByLogin(String login);

    List<User> getUsersByIds(List<Long> distinctUserIds);

    Optional<User> getUserById(long userId);
}
