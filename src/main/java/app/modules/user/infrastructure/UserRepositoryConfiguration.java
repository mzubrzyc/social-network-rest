package app.modules.user.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfiguration {

    @Bean
    public JpaUserRetrievingAdapter jpaUserRetrievingAdapter(JpaUserRepository jpaUserRepository) {
        return new JpaUserRetrievingAdapter(jpaUserRepository);
    }

    @Bean
    public JpaUserModificationAdapter jpaUserModificationAdapter(JpaUserRepository jpaUserRepository) {
        return new JpaUserModificationAdapter(jpaUserRepository);
    }

}
