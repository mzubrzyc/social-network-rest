package app.modules.user.infrastructure;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    List<UserEntity> findAllByUserIdIn(List<Long> userIds);

    Optional<UserEntity> findUserEntityByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "update users set name = :name, surname = :surname,  nick = :nick where user_id = :userId", nativeQuery = true)
    void updateUser(
        @Param("userId") Long userId,
        @Param("name") String name,
        @Param("surname") String surname,
        @Param("nick") String nick
    );

}
