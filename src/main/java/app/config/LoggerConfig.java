package app.config;

import java.io.File;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

@UtilityClass
@Slf4j
public class LoggerConfig {

    public static void load(){
        final LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        File file = new File("log4j2.yaml");
        loggerContext.setConfigLocation(file.toURI());
        loggerContext.reconfigure();
        log.info("Loading Log4j config file: {}", file.getName());
        log.info("Logger: {}", loggerContext.getLoggers());
    }

}
