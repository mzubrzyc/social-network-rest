package app.modules.user.post.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryConfiguration {

    @Bean
    public PostRetrievingAdapter jpaPostRetrievingAdapter(PostJpaRepository postJpaRepository) {
        return new PostRetrievingAdapter(postJpaRepository);
    }

    @Bean
    public PostModificationAdapter jpaPostModificationAdapter(PostJpaRepository postJpaRepository) {
        return new PostModificationAdapter(postJpaRepository);
    }

}
