package com.samtel.core.services;

import com.samtel.adapters.secondary.rest.identidad.MockClienteDTORequest;
import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.core.services.impl.ValidarIdentidadServiceImpl;
import com.samtel.ports.secondary.rest.IdentidadService;
import com.samtel.ports.secondary.rest.OTPService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ValidarIdentidadServiceTest {
    private ValidarIdentidadService validarIdentidadService;
    @Mock
    private IdentidadService identidadService;
    @Mock
    private OTPService otpService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarIdentidadService = new ValidarIdentidadServiceImpl(identidadService, otpService);
    }

    @Test
    public void testValidarIdentidad() throws JSONException {
        final ResponseDTO responseDTO = ResponseDTO.builder()
                .respuestaServicio("123456")
                .codRespuesta("1")
                .resultError("null")
                .build();
        Mockito.when(identidadService.validarIdentidad(MockClienteDTORequest.getClientDTO())).thenReturn(Optional.of(responseDTO));
        Optional<ResponseDTO> result = validarIdentidadService.validar(MockClienteDTORequest.getClientDTO());
        Assert.assertNotNull(result);
    }

}
