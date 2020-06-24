package co.com.santander.adapters.secondary.rest.identidad;

import co.com.santander.core.dto.*;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class IdentidadServiceTest_IT {

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

    @Test
    public void testValidarObtenerPreguntasReto() {
        WireMockServer wireMockServerPreguntasReto = new WireMockServer(7081);
        wireMockServerPreguntasReto.start();
        configureFor("localhost", 7081);
        stubFor(post(urlEqualTo("/evidente/preguntas")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responsePreguntasReto.json")));
        DatosAdicionalesDTO datosAdicionalesDTO = DatosAdicionalesDTO.builder()
                .codigoOTP("ccvcvcv")
                .datosBasicosDTO(DatosBasicosDTO.builder()
                        .usuarioAliado("12112")
                        .tipoIdentificacion("1")
                        .sesionId("121212121")
                        .numeroSolicitudCredito("121212")
                        .ipOrigen("12.121.21.21")
                        .identificacion("12121212")
                        .codAliado("1")
                        .build())
                .idTransaccionOTP("121212")
                .numeroCelular("1121")
                .regValidacion("121212")
                .build();
        Optional<ResponseDTO> result = identidadService.obtenerPreguntasReto(datosAdicionalesDTO);
        Assert.assertNotNull(result);
        wireMockServerPreguntasReto.stop();

    }

    @Test
    public void testValidarPreguntasReto() {
        List<RespuestaCuestionarioDTO> listaRespuestas = new ArrayList<>();
        listaRespuestas.add(RespuestaCuestionarioDTO.builder()
                .idPregunta("1212")
                .idRespuesta("121")
                .build());
        WireMockServer wireMockServerValidarPreguntasReto = new WireMockServer(7081);
        wireMockServerValidarPreguntasReto.start();
        configureFor("localhost", 7081);
        stubFor(post(urlEqualTo("/evidente/verificar")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseVerificarPreguntas.json")));
        Optional<ResponseDTO> result = identidadService.validarPreguntasReto(CuestionarioDTO.builder()
                .datosBasicosDTO(DatosBasicosDTO.builder()
                        .usuarioAliado("12112")
                        .tipoIdentificacion("1")
                        .sesionId("121212121")
                        .numeroSolicitudCredito("121212")
                        .ipOrigen("12.121.21.21")
                        .identificacion("12121212")
                        .codAliado("1")
                        .build())
                .idCuestionario("121212")
                .regCuestionario("12121212")
                .respuestas(listaRespuestas)
                .build());
        Assert.assertNotNull(result);
        wireMockServerValidarPreguntasReto.stop();

    }

    @After
    public void after() {
        wireMockServer.stop();
    }
}
