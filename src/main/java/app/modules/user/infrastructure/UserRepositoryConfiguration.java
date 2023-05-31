package app.modules.user.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfiguration {

    @Bean
    public UserRetrievingAdapter jpaUserRetrievingAdapter(UserJpaRepository userJpaRepository) {
        return new UserRetrievingAdapter(userJpaRepository);
    }

    @Bean
    public UserModificationAdapter jpaUserModificationAdapter(UserJpaRepository userJpaRepository) {
        return new UserModificationAdapter(userJpaRepository);
    }

}
