package co.com.santander.adapters.secondary.rest.otp;

import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.secondary.rest.OTPService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class OTPServiceTest_IT {
    @Autowired
    private OTPService otpService;
    public WireMockServer wireMockServer = new WireMockServer(7080);
    private DatosAdicionalesDTO datosAdicionalesDTO;

    @Before
    public void setUp() {
        wireMockServer.start();
        configureFor("localhost", 7080);
        stubFor(post(urlEqualTo("/auth/realms/3scale-api/protocol/openid-connect/token")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseKeyCloak.json")));
        datosAdicionalesDTO = DatosAdicionalesDTO.builder()
                .datosBasicosDTO(DatosBasicosDTO.builder()
                        .usuarioAliado("12112")
                        .tipoIdentificacion("1")
                        .sesionId("121212121")
                        .numeroSolicitudCredito("121212")
                        .ipOrigen("12.121.21.21")
                        .identificacion("12121212")
                        .codAliado("1")
                        .build())
                .regValidacion("121212")
                .build();
    }

    @Test
    public void testGenerarOTP() {
        datosAdicionalesDTO.setIdTransaccionOTP("12112");
        datosAdicionalesDTO.setNumeroCelular("212121");
        WireMockServer wireMockGenerarOtp = new WireMockServer(7081);
        wireMockGenerarOtp.start();
        configureFor("localhost", 7081);
        stubFor(post(urlEqualTo("/evidente/generarotp")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseGenerarOtp.json")));
        Optional<ResponseDTO> result = otpService.generarOTP(datosAdicionalesDTO);
        Assert.assertNotNull(result);
        wireMockGenerarOtp.stop();
    }

    @Test
    public void testIniciarTransaccion() {
        WireMockServer wireMockIniciarTransaccion = new WireMockServer(7081);
        wireMockIniciarTransaccion.start();
        configureFor("localhost", 7081);
        stubFor(post(urlEqualTo("/evidente/iniciartransotp")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseIniciarTransaccionOTP.json")));
        Optional<ResponseDTO> result = otpService.iniciarTransaccion(datosAdicionalesDTO);
        Assert.assertNotNull(result);
        wireMockIniciarTransaccion.stop();
    }

    @Test
    public void testVerificarOTP() {
        datosAdicionalesDTO.setIdTransaccionOTP("12112");
        datosAdicionalesDTO.setCodigoOTP("1211221");
        WireMockServer wireMockIniciarTransaccion = new WireMockServer(7081);
        wireMockIniciarTransaccion.start();
        configureFor("localhost", 7081);
        stubFor(post(urlEqualTo("/evidente/verificarotp")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBodyFile("responseVerificarOtp.json")));
        Optional<ResponseDTO> result = otpService.verificarOTP(datosAdicionalesDTO);
        Assert.assertNotNull(result);
        wireMockIniciarTransaccion.stop();
    }
}
