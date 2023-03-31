package app.fixture.user;

import app.modules.user.infrastructure.JpaUserRepository;
import app.modules.user.infrastructure.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class JpaUserFixture {

    @Autowired
    JpaUserRepository jpaUserRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        return jpaUserRepository.saveAndFlush(userEntity);
    }

    public List<UserEntity> saveUsers(List<UserEntity> userEntityList) {
        return jpaUserRepository.saveAll(userEntityList);
    }

}
