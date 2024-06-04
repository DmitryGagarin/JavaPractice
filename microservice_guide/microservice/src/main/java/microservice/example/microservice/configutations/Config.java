package microservice.example.microservice.configutations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties("limits-service")
@Setter
@Getter
public class Config {

    private int maximum;
    private int minimum;;


}
