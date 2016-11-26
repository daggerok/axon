package daggerok;

import daggerok.config.AmqpConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AmqpConfig.class)
public class ComplaintsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintsApplication.class, args);
    }
}
