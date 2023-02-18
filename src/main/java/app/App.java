package app;

import app.config.LoggerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class App {

    public static void main(String[] args) {
        LoggerConfig.load();
        SpringApplication.run(App.class, args);
    }

}
