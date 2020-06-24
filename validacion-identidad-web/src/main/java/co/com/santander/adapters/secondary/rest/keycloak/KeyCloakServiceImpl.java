package co.com.santander.adapters.secondary.rest.keycloak;

import co.com.santander.core.dto.KeyCloakRequestDto;
import co.com.santander.core.dto.ResponseKeyCloakDto;
import co.com.santander.ports.secondary.rest.KeyCloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeyCloakServiceImpl implements KeyCloakService {

    private final KeyCloakRequestDto keyCloakRequestDto;
    private final RestTemplate restTemplate;

    @Autowired
    public KeyCloakServiceImpl(KeyCloakRequestDto keyCloakRequestDto, RestTemplate restTemplate) {
        this.keyCloakRequestDto = keyCloakRequestDto;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getToken() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> request = keyCloakRequestDto.getParams();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(request, requestHeaders);
        ResponseEntity<ResponseKeyCloakDto> result = restTemplate.exchange(keyCloakRequestDto.getUrl(), HttpMethod.POST, requestEntity, ResponseKeyCloakDto.class);
        return result.getBody().getAccess_token();
    }
}
