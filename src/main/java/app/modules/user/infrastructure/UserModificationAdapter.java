package app.modules.user.infrastructure;

import app.modules.user.User;
import app.modules.user.UserModificationPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserModificationAdapter implements UserModificationPort {

    UserJpaRepository userJpaRepository;

    @Override
    public void saveNewUser(User user) throws Exception {
        userJpaRepository.saveAndFlush(UserEntityMapper.toUserEntity(user));
    }

    @Override
    public void updateUser(User user) throws Exception {
        userJpaRepository.updateUser(
            user.getUserId(),
            user.getName(),
            user.getSurname(),
            user.getNick()
        );
    }

    @Override
    public void deleteUser(User user) throws Exception {
        userJpaRepository.deleteById(user.getUserId());
    }
}
