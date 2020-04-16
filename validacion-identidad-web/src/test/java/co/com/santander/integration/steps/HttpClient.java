package co.com.santander.integration.steps;

import co.com.santander.adapters.primary.rest.dto.Response;
import co.com.santander.adapters.primary.rest.identidad.input.ClienteInput;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HttpClient {
    private final String SERVER_URL = "http://localhost";
    private final String EXAMPLE_ENDPOINT = "/validacion";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String exampleEndPoint() {
        return SERVER_URL + ":" + port + EXAMPLE_ENDPOINT;
    }

    public String get(String resource) {
        return restTemplate.getForEntity(exampleEndPoint().concat(resource), String.class).getBody();
    }

    public ResponseEntity<?> getWithRequest(String resource, ClienteInput payload) {
        System.out.println(exampleEndPoint().concat(resource));
        return restTemplate.getForEntity(exampleEndPoint().concat(resource), ClienteInput.class, payload);
    }

    public ResponseEntity<?> postWithRequest(String resource, Object payload){
        System.out.println(exampleEndPoint().concat(resource));
        HttpEntity<Object> request = new HttpEntity<>(payload);
        return restTemplate.exchange(exampleEndPoint().concat(resource), HttpMethod.POST,request, Response.class);
    }
}
