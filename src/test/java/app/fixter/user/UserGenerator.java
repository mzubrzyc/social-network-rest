package app.fixter.user;

import app.modules.user.User;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class UserGenerator {

    private static final int MAX_LOGIN_LENGTH = 15;
    private static final int MAX_NAME_LENGTH = 10;
    private final Random random = new Random();

    public List<User> generateUsers(int numberOfUsers) {
        return Stream.generate(UserGenerator::generateUser)
                     .limit(numberOfUsers)
                     .toList();
    }

    private User generateUser() {
        return new User(RandomStringUtils.randomAlphabetic(random.nextInt(7, MAX_LOGIN_LENGTH)),
                        RandomStringUtils.randomAlphabetic(random.nextInt(1, MAX_NAME_LENGTH)),
                        RandomStringUtils.randomAlphabetic(random.nextInt(1, MAX_NAME_LENGTH)),
                        RandomStringUtils.randomAlphabetic(random.nextInt(1, MAX_NAME_LENGTH))
        );
    }

}
