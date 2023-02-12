package app.modules.user;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFacade {

    private final UserRetrievingService userRetrievingService;
    private final UserModificationService userModificationService;

    public List<User> getAllUsers() {
        return userRetrievingService.getAllUsers();
    }

    public Optional<User> getUserByLogin(String login) {
        return userRetrievingService.getUserByLogin(login);
    }

    public void saveNewUser(User user) throws Exception {
        Optional<User> optionalExistingUser = getUserByLogin(user.getLogin());
        userModificationService.saveNewUser(optionalExistingUser, user);
    }

    public void deleteUser(User user) throws Exception {
        Optional<User> optionalExistingUser = getUserByLogin(user.getLogin());
        userModificationService.deleteUser(optionalExistingUser, user);
    }

    public List<User> getUsersByIds(List<Long> distinctUserIds) {
        return userRetrievingService.getUsersByIds(distinctUserIds);
    }
}
