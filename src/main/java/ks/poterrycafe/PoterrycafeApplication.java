package ks.poterrycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class PoterrycafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoterrycafeApplication.class, args);
    }

}
