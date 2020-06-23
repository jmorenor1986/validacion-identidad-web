package co.com.santander.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "keycloak.client")
public class KeyCloakRequestDto {
    @JsonProperty("client-id")
    private String clientId;
    @JsonProperty("client-secret")
    private String clientSecret;
    @JsonProperty("grant-type")
    private String grantType;
    @JsonProperty("url")
    private String url;

    public MultiValueMap<String, String> getParams() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("client_secret", getClientSecret());
        map.add("grant_type", getGrantType());
        map.add("client_id", getClientId());
        return map;
    }
}
