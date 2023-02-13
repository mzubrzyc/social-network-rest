package app.modules.user;

import java.time.LocalDate;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserModificationService {

    private final UserModificationPort userModificationPort;

    public void saveNewUser(Optional<User> optionalExistingUser, User user) throws Exception {
        throwExceptionIfUserAlreadyExists(optionalExistingUser);
        user.setCreationDate(LocalDate.now());
        userModificationPort.saveNewUser(user);
    }

    public void deleteUser(Optional<User> optionalExistingUser, User user) throws Exception {
        throwExceptionIfUserDoesNotExistInDb(optionalExistingUser, user);
        userModificationPort.deleteUser(user);
    }

    private void throwExceptionIfUserAlreadyExists(Optional<User> optionalExistingUser) throws Exception {
        if (optionalExistingUser.isPresent()) {
            throw new Exception("User of login: " + optionalExistingUser.get().getLogin() + " already exists in database");
        }
    }

    private void throwExceptionIfUserDoesNotExistInDb(Optional<User> optionalExistingUser, User user) throws Exception {
        if (optionalExistingUser.isEmpty()) {
            throw new Exception("User of login: " + user.getLogin() + " does NOT exist in database");
        }
    }

    public void deleteUserById(Optional<User> optionalExistingUser, long userId) throws Exception {
        User user = optionalExistingUser
            .orElseThrow(() -> new Exception("Can't delete the. User of id: " + userId + " does NOT exist in database"));
        throwExceptionIfUserDoesNotExistInDb(optionalExistingUser, userId);
        userModificationPort.deleteUser(user);
    }

    private void throwExceptionIfUserDoesNotExistInDb(Optional<User> optionalExistingUser, long userId) throws Exception {
        if (optionalExistingUser.isEmpty()) {
            throw new Exception("Can't delete the. User of id: " + userId + " does NOT exist in database");
        }
    }

    public void updateUser(Optional<User> optionalExisting, User user) throws Exception {
        throwExceptionIfUserDoesNotExistInDb(optionalExisting, user);
        userModificationPort.updateUser(user);
    }
}
