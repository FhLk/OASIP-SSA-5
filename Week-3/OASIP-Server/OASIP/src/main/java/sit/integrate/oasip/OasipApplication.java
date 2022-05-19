package sit.integrate.oasip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class OasipApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasipApplication.class, args);
    }
}
