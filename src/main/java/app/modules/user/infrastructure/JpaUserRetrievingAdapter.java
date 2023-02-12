package app.modules.user.infrastructure;

import app.modules.user.User;
import app.modules.user.UserRetrievingPort;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JpaUserRetrievingAdapter implements UserRetrievingPort {

    JpaUserRepository jpaUserRepository;

    @Override
    public List<User> getAllUsers() {
        return UserEntityMapper.fromUserEntity(jpaUserRepository.findAll());
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Optional<UserEntity> optionalUserEntity = jpaUserRepository.findByLogin(login);
        return optionalUserEntity.map(UserEntityMapper::fromUserEntity);
    }

    @Override
    public List<User> getUsersByIds(List<Long> distinctUserIds) {
        return UserEntityMapper.fromUserEntity(jpaUserRepository.findAllByUserIdIn(distinctUserIds));
    }
}
