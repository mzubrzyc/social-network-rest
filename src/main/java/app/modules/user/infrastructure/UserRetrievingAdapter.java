package app.modules.user.infrastructure;

import app.modules.user.User;
import app.modules.user.UserRetrievingPort;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRetrievingAdapter implements UserRetrievingPort {

    UserJpaRepository userJpaRepository;

    @Override
    public List<User> getAllUsers() {
        return UserEntityMapper.fromUserEntity(userJpaRepository.findAll());
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findByLogin(login);
        return optionalUserEntity.map(UserEntityMapper::fromUserEntity);
    }

    @Override
    public List<User> getUsersByIds(List<Long> distinctUserIds) {
        return UserEntityMapper.fromUserEntity(userJpaRepository.findAllByUserIdIn(distinctUserIds));
    }

    @Override
    public Optional<User> getUserById(long userId) {
        Optional<UserEntity> userEntityByUserId = userJpaRepository.findUserEntityByUserId(userId);
        return userEntityByUserId.map(UserEntityMapper::fromUserEntity);
    }
}
