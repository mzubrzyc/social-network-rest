package app.testconfig;

import app.fixture.post.JpaPostFixture;
import app.fixture.user.JpaUserFixture;
import app.utils.TableEraser;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@Import(
    {
        TableEraser.class,
        JpaUserFixture.class,
        JpaPostFixture.class,
    }
)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationTest {
}
