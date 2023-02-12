package app.modules.user.post.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryConfiguration {

    @Bean
    public JpaPostRetrievingAdapter jpaPostRetrievingAdapter(JpaPostRepository jpaPostRepository) {
        return new JpaPostRetrievingAdapter(jpaPostRepository);
    }

    @Bean
    public JpaPostModificationAdapter jpaPostModificationAdapter(JpaPostRepository jpaPostRepository) {
        return new JpaPostModificationAdapter(jpaPostRepository);
    }

}
