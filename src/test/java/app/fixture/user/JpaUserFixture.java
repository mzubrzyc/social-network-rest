package app.fixture.user;

import app.modules.user.infrastructure.UserJpaRepository;
import app.modules.user.infrastructure.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class JpaUserFixture {

    @Autowired
    UserJpaRepository userJpaRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        return userJpaRepository.saveAndFlush(userEntity);
    }

    public List<UserEntity> saveUsers(List<UserEntity> userEntityList) {
        return userJpaRepository.saveAll(userEntityList);
    }

}
