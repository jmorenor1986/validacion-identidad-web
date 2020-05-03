package co.com.santander.core.services;

import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.services.command.GenerarOTPCommandImpl;
import co.com.santander.core.services.command.IniciarOTPCommandImpl;
import co.com.santander.core.services.command.PreguntasRetoCommandImpl;
import co.com.santander.core.services.command.ValidarIdentidadCommandImpl;
import co.com.santander.core.services.impl.ValidarIdentidadServiceImpl;
import co.com.santander.ports.primary.ValidarIdentidadService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class ValidarIdentidadServiceTest {
    private ValidarIdentidadService validarIdentidadService;
    @Mock
    private ValidarIdentidadCommandImpl validarIdentidadCommand;
    @Mock
    private IniciarOTPCommandImpl iniciarOTPCommand;
    @Mock
    private PreguntasRetoCommandImpl preguntasRetoCommand;
    @Mock
    private GenerarOTPCommandImpl generarOTPCommand;

    private DatosBasicosDTO datosBasicosDTO;

    private ClienteDTO clienteDTO;

    private Map<String, Object> validarIdentidad;
    private Map<String, Object> iniciarOTP;
    private Map<String, Object> generarOTP;
    private Map<String, Object> preguntasReto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        datosBasicosDTO = DatosBasicosDTO.builder()
                .codAliado("1234")
                .identificacion("1234")
                .ipOrigen("1212")
                .numeroSolicitudCredito("12122")
                .sesionId("12121212")
                .tipoIdentificacion("1")
                .usuarioAliado("121212")
                .build();

        clienteDTO = ClienteDTO.builder()
                .datosBasicosDTO(datosBasicosDTO)
                .fechaExpedicion("1212121212")
                .numeroCelular("1121212")
                .primerApellido("1212121212")
                .primerNombre("112121212")
                .segundoApellido("12121212")
                .segundoNombre("12121212")
                .build();
        validarIdentidad = new HashMap<>();
        iniciarOTP = new HashMap<>();
        generarOTP = new HashMap<>();
        preguntasReto = new HashMap<>();
        validarIdentidadService = new ValidarIdentidadServiceImpl(validarIdentidadCommand, iniciarOTPCommand, preguntasRetoCommand, generarOTPCommand);
    }

    @Test
    public void testValidarIdentidadClienteVaibleOTP() throws JSONException {
        validarIdentidad.put("resultado", "01");
        validarIdentidad.put("regValidacion", "12345");
        Mockito.when(validarIdentidadCommand.callService(clienteDTO)).thenReturn(Optional.of(validarIdentidad));
        iniciarOTP.put("codResultadoOTP", "4");
        iniciarOTP.put("idTransaccionOTP", "1212121");
        Mockito.when(iniciarOTPCommand.callService(Mockito.any())).thenReturn(Optional.of(iniciarOTP));
        generarOTP.put("codResultadoOTP", "4");
        generarOTP.put("idTransaccionOTP", "1212121");
        Mockito.when(generarOTPCommand.callService(Mockito.any())).thenReturn(Optional.of(generarOTP));
        Optional<ResponseDTO> result = validarIdentidadService.validar(clienteDTO);
        Assert.assertNotNull(result);
        Assert.assertEquals("2", result.get().getCodRespuesta());
    }

    @Test
    public void testValidarIdentidadClienteVaiblePreguntasReto() throws JSONException {
        validarIdentidad.put("resultado", "01");
        validarIdentidad.put("regValidacion", "12345");
        Mockito.when(validarIdentidadCommand.callService(clienteDTO)).thenReturn(Optional.of(validarIdentidad));
        iniciarOTP.put("codResultadoOTP", "99");
        iniciarOTP.put("idTransaccionOTP", "1212121");
        Mockito.when(iniciarOTPCommand.callService(Mockito.any())).thenReturn(Optional.of(iniciarOTP));
        preguntasReto.put("Preguntas", ResponseServicesMock.preguntasReto);
        Mockito.when(preguntasRetoCommand.callService(Mockito.any())).thenReturn(Optional.of(preguntasReto));
        Optional<ResponseDTO> result = validarIdentidadService.validar(clienteDTO);
        Assert.assertNotNull(result);
        Assert.assertEquals("3", result.get().getCodRespuesta());
    }

    @Test
    public void testValidarIdentidadClienteNoViableReintentos() throws JSONException {
        validarIdentidad.put("resultado", "10");
        validarIdentidad.put("regValidacion", "12345");
        Mockito.when(validarIdentidadCommand.callService(clienteDTO)).thenReturn(Optional.of(validarIdentidad));
        Optional<ResponseDTO> result = validarIdentidadService.validar(clienteDTO);
        Assert.assertNotNull(result);
        Assert.assertEquals("1", result.get().getCodRespuesta());
    }

    @Test
    public void testValidarIdentidadClienteNoViable() throws JSONException {
        validarIdentidad.put("resultado", "09");
        validarIdentidad.put("regValidacion", "12345");
        Mockito.when(validarIdentidadCommand.callService(clienteDTO)).thenReturn(Optional.of(validarIdentidad));
        Optional<ResponseDTO> result = validarIdentidadService.validar(clienteDTO);
        Assert.assertNotNull(result);
        Assert.assertEquals("0", result.get().getCodRespuesta());
    }

}
