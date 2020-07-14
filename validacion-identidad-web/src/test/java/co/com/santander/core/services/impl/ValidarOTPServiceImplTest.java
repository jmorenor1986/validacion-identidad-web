package co.com.santander.core.services.impl;

import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.services.command.PreguntasRetoCommandImpl;
import co.com.santander.core.services.command.ValidarOTPCommandImpl;
import co.com.santander.ports.primary.ValidarOTPService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ValidarOTPServiceImplTest {
    private ValidarOTPService validarOTPService;
    @Mock
    private ValidarOTPCommandImpl validarOTPCommand;
    @Mock
    private PreguntasRetoCommandImpl preguntasRetoCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarOTPService = new ValidarOTPServiceImpl(validarOTPCommand, preguntasRetoCommand);
    }

    @Test(expected = BusinessException.class)
    public void testValidarOtpValidaOtpError() throws JSONException {
        DatosAdicionalesDTO datosAdicionalesDTO = DatosAdicionalesDTO.builder()
                .regValidacion("1121212")
                .numeroCelular("121121")
                .idTransaccionOTP("121212122")
                .codigoOTP("12112")
                .build();
        Mockito.when(validarOTPCommand.callService(datosAdicionalesDTO)).thenReturn(Optional.empty());
        Optional<ResponseDTO> result = validarOTPService.validarOTP(datosAdicionalesDTO);
    }

}