package co.com.santander.core.services;

import co.com.santander.adapters.secondary.rest.identidad.MockClienteDTORequest;
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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarIdentidadService = new ValidarIdentidadServiceImpl(validarIdentidadCommand, iniciarOTPCommand, preguntasRetoCommand, generarOTPCommand);
    }

    @Test
    public void testValidarIdentidad() throws JSONException {
        final ResponseDTO responseDTO = ResponseDTO.builder()
                .respuestaServicio("123456")
                .codRespuesta("1")
                .mensajeError("null")
                .build();
        Optional<ResponseDTO> result = validarIdentidadService.validar(MockClienteDTORequest.getClientDTO());
        Assert.assertNotNull(result);
    }

}
