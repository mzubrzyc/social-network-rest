package app.modules.user.infrastructure;

import app.modules.user.User;
import app.modules.user.UserModificationPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JpaUserModificationAdapter implements UserModificationPort {

    JpaUserRepository jpaUserRepository;

    @Override
    public void saveNewUser(User user) throws Exception {
        jpaUserRepository.saveAndFlush(UserEntityMapper.toUserEntity(user));
    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public void deleteUser(User user) throws Exception {
        jpaUserRepository.deleteById(user.getUserId());
    }
}
