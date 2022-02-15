package su.mingchang.blogservice;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.net.InetAddress;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class BlogServiceApplication {

    @Value("${dbPass}")
    private String dbPass = "";

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("operate_log");
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            logger.info("[Pre-startup Setting] Hostname: {}", hostName);
        } catch (Exception e) {
            logger.error("[Pre-startup Setting] Failed to check hostname, will continue with dev profile.");
        }
        if (hostName.equals("TBD")) {
            logger.info("[Pre-startup Setting] Set prod as active profile.");
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod");
        } else {
            logger.info("[Pre-startup Setting] Set dev as active profile.");
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        }
        logger.info("[Pre-startup Setting] Pre-startup setting finished, starting Spring Application.");
        SpringApplication.run(BlogServiceApplication.class, args);
    }

    @Bean
    public ConnectionFactory createConnectionFactory() {
        Logger logger = LoggerFactory.getLogger("operate_log");
        Scanner scanner = new Scanner(System.in);
        String activeProfile = System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME);

        if (Objects.equals(activeProfile, "dev")) {
            if (dbPass.equals("")) {
                logger.info("[Post-startup Setting] Enter database password: ");
                dbPass = scanner.next();
            } else {
                logger.info("[Post-startup Setting] Got database password from command line argument, continue.");
            }
        } else {
            logger.info("[Post-startup Setting] Not running in development profile, set default database password.");
            dbPass = "root";
        }

        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .database("postgres")
                        .schema("blog")
                        .username("root")
                        .password(dbPass)
                        .build()
        );
    }

}
