package sit.integrate.oasip;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("api/**")
                .allowedOrigins(
                        "http://10.4.56.111",
                        "http://ip21ssa5.sit.kmutt.ac.th"
                )
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}
