package co.com.santander.adapters.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "otp")
@Getter
@Setter
public class OtpProperties {
    private String codigoValidacion;
}
