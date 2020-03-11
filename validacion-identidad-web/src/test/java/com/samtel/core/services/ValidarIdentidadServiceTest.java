package com.samtel.core.services;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.core.services.impl.ValidarIdentidadServiceImpl;
import com.samtel.ports.secondary.rest.IdentidadService;
import com.samtel.ports.secondary.rest.OTPService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ValidarIdentidadServiceTest {
    private ValidarIdentidadService validarIdentidadService;
    private ClienteDTO clienteDTO;
    private IdentidadService identidadService;
    private OTPService otpService;

    @Before
    public void setUp() {
        validarIdentidadService = new ValidarIdentidadServiceImpl(identidadService, otpService);
    }

    @Test
    public void testValidarIdentidad() {
        Optional<ResponseDTO> result = validarIdentidadService.validar(clienteDTO);
    }

}
