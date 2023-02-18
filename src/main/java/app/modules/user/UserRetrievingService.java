package app.modules.user;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
class UserRetrievingService {

    private final UserRetrievingPort userRetrievingPort;

    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRetrievingPort.getAllUsers();
    }

    public Optional<User> getUserByLogin(String login) {
        log.info("Getting user of login {}", login);
        return userRetrievingPort.getUserByLogin(login);
    }

    public List<User> getUsersByIds(List<Long> distinctUserIds) {
        log.info("Getting users for ids {}", distinctUserIds);
        return userRetrievingPort.getUsersByIds(distinctUserIds);
    }

    public Optional<User> getUserById(long userId) {
        log.info("Getting user of id {}", userId);
        return userRetrievingPort.getUserById(userId);
    }
}
