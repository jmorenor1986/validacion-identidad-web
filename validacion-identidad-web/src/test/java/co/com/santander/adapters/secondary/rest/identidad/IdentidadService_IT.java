package co.com.santander.adapters.secondary.rest.identidad;

import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.secondary.rest.IdentidadService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
@ActiveProfiles("test")
public class IdentidadService_IT {

    @Autowired
    private IdentidadService identidadService;
    public WireMockServer wireMockServer = new WireMockServer(7080);
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
        configureFor("localhost", 7080);
        stubFor(post(urlEqualTo("/auth/realms/3scale-api/protocol/openid-connect/token")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseKeyCloak.json")));
    }

    @Test
    public void testValidarIdentidadSuccess() throws JSONException {
        WireMockServer wireMockServerValidarIdentidad = new WireMockServer(7081);
        wireMockServerValidarIdentidad.start();
        configureFor("localhost", 7081);
        stubFor(post(urlEqualTo("/evidente/validar")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseValidarIdentidad.json")));
        Optional<ResponseDTO> result = identidadService.validarIdentidad(clienteDTO);
        Assert.assertNotNull(result);
        wireMockServerValidarIdentidad.stop();
    }

    @After
    public void after() {
        wireMockServer.stop();
    }
}
