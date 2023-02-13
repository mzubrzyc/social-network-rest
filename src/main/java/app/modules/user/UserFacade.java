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
        userModificationService.saveNewUser(getUserByLogin(user.getLogin()), user);
    }

    public void deleteUserById(long userId) throws Exception {
        userModificationService.deleteUserById(userRetrievingService.getUserById(userId), userId);
    }

    public void deleteUser(User user) throws Exception {
        userModificationService.deleteUser(getUserByLogin(user.getLogin()), user);
    }

    public List<User> getUsersByIds(List<Long> distinctUserIds) {
        return userRetrievingService.getUsersByIds(distinctUserIds);
    }

    public void updateUser(User user) throws Exception {
        userModificationService.updateUser(userRetrievingService.getUserById(user.getUserId()), user);
    }
}
