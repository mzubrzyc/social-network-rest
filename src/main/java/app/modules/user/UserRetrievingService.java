package app.modules.user;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserRetrievingService {

    private final UserRetrievingPort userRetrievingPort;

    public List<User> getAllUsers() {
        return userRetrievingPort.getAllUsers();
    }

    public Optional<User> getUserByLogin(String login) {
        return userRetrievingPort.getUserByLogin(login);
    }

    public List<User> getUsersByIds(List<Long> distinctUserIds) {
        return userRetrievingPort.getUsersByIds(distinctUserIds);
    }
}
