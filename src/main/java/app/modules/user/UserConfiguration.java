package app.modules.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserFacade authorFacade(
        UserRetrievingService userRetrievingService,
        UserModificationService userModificationService
    ) {
        return new UserFacade(userRetrievingService,
                              userModificationService);
    }

}
