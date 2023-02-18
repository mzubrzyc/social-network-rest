package app.modules.user;

import java.time.LocalDate;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
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
            log.error("User of login {} already exists in db", optionalExistingUser.get().getLogin());
            throw new Exception("User of login: " + optionalExistingUser.get().getLogin() + " already exists in database");
        }
    }

    private void throwExceptionIfUserDoesNotExistInDb(Optional<User> optionalExistingUser, User user) throws Exception {
        if (optionalExistingUser.isEmpty()) {
            log.error("User of login {} does not exist in db", user.getLogin());
            throw new Exception("User of login: " + user.getLogin() + " does NOT exist in database");
        }
    }

    public void deleteUserById(Optional<User> optionalExistingUser, long userId) throws Exception {
        User user = optionalExistingUser
            .orElseThrow(() -> {
                log.error("Can not delete the User of id {}, cause it does NOT exist in database", userId);
                return new Exception("Can not delete the User of id: " + userId + " does NOT exist in database");
            });
        userModificationPort.deleteUser(user);
    }

    public void updateUser(Optional<User> optionalExisting, User user) throws Exception {
        log.info("Updating user of login {}", user.getLogin());
        throwExceptionIfUserDoesNotExistInDb(optionalExisting, user);
        userModificationPort.updateUser(user);
    }
}
