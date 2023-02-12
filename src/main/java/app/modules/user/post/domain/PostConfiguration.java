package app.modules.user.post.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfiguration {

    @Bean
    public PostFacade postFacade(
        PostRetrievingService postRetrievingService,
        PostModificationService postModificationService
    ) {
        return new PostFacade(
            postRetrievingService,
            postModificationService
        );
    }

}
