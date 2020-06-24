package co.com.santander.adapters.secondary.rest.keycloak;

import co.com.santander.core.dto.KeyCloakRequestDto;
import co.com.santander.core.dto.ResponseKeyCloakDto;
import co.com.santander.ports.secondary.rest.KeyCloakService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class KeyCloakServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    private KeyCloakService keyCloakService;
    private KeyCloakRequestDto keyCloakRequestDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        keyCloakRequestDto = new KeyCloakRequestDto();
        keyCloakRequestDto.setClientId("1212");
        keyCloakRequestDto.setClientSecret("121212");
        keyCloakRequestDto.setGrantType("121212");
        keyCloakRequestDto.setUrl("1211212");
        keyCloakService = new KeyCloakServiceImpl(keyCloakRequestDto, restTemplate);
    }

    @Test
    public void testGetToken() {
        ResponseKeyCloakDto responseKeyCloakDto = new ResponseKeyCloakDto();
        responseKeyCloakDto.setAccess_token("1221212122");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> request = keyCloakRequestDto.getParams();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(request, requestHeaders);
        Mockito.when(restTemplate.exchange(
                Mockito.anyString(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<?>>any(), Mockito.<Class<ResponseKeyCloakDto>>any())).
                thenReturn(new ResponseEntity<>(responseKeyCloakDto, HttpStatus.OK));
        String result = keyCloakService.getToken();
        Assert.assertNotNull(result);
    }


}