package co.com.santander.adapters.secondary.rest.clients;

import co.com.santander.core.dto.KeyCloakRequestDto;
import co.com.santander.ports.secondary.rest.KeyCloakService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    private final KeyCloakService keyCloakService;
    private final KeyCloakRequestDto keyCloakRequestDto;

    @Autowired
    public FeignClientInterceptor(KeyCloakService keyCloakService, KeyCloakRequestDto keyCloakRequestDto) {
        this.keyCloakService = keyCloakService;
        this.keyCloakRequestDto = keyCloakRequestDto;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (null != keyCloakRequestDto.getEnable() && keyCloakRequestDto.getEnable()) {
            FeignClientInterceptor.log.info("GENERATE TOKEN Service {}", requestTemplate.url());
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, keyCloakService.getToken()));
        } else {
            FeignClientInterceptor.log.info("TOKEN DISABLED Service {}", requestTemplate.url());
        }

    }
}
