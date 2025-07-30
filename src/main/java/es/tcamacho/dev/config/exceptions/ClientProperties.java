package es.tcamacho.dev.config.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "client")
public class ClientProperties {
    private String baseUrl;

}
