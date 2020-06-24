package co.com.santander.adapters.secondary.rest.identidad;

import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
import co.com.santander.ports.secondary.rest.IdentidadService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
public class IdentidadService_IT {

    @Autowired
    private IdentidadService identidadService;
    @Rule
    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().notifier(new Slf4jNotifier(true)));
    private ClienteDTO clienteDTO;

    @Before
    public void setUp() {
        wireMockServer.start();
        clienteDTO = ClienteDTO.builder()
                .primerNombre("john")
                .segundoApellido("Rojas")
                .primerApellido("Moreno")
                .numeroCelular("3013423333")
                .fechaExpedicion("121212")
                .datosBasicosDTO(DatosBasicosDTO.builder()
                        .usuarioAliado("12112")
                        .tipoIdentificacion("1")
                        .sesionId("121212121")
                        .numeroSolicitudCredito("121212")
                        .ipOrigen("12.121.21.21")
                        .identificacion("12121212")
                        .codAliado("1")
                        .build())
                .segundoNombre("")
                .build();
        configureFor("localhost", 8080);
        stubFor(post(urlEqualTo("/auth/realms/3scale-api/protocol/openid-connect/token")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseKeyCloak.json")));
    }

    @Test
    public void testValidarIdentidadSuccess() {
        WireMockServer wireMockServerValidarIdentidad = new WireMockServer(new WireMockConfiguration().notifier(new Slf4jNotifier(true)));
        configureFor("localhost", 8081);
        stubFor(post(urlEqualTo("/auth/realms/3scale-api/protocol/openid-connect/token")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseKeyCloak.json")));
    }

    @After
    public void after() {
        wireMockServer.stop();
    }
}
