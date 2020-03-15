package com.samtel.adapters.secondary.rest.identidad;

import com.samtel.adapters.common.utilities.JsonUtilities;
import com.samtel.adapters.primary.rest.identidad.mapper.IdentificacionConverterDtoToPayload;
import com.samtel.adapters.secondary.rest.clients.IdentificacionCliente;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.ports.secondary.rest.IdentidadService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class IdentidadServiceImplTest {

    @Autowired
    private IdentidadService identidadService;
    private IdentificacionConverterDtoToPayload identificacionMapperExt;
    @Autowired
    private JsonUtilities jsonUtilities;

    @Autowired
    IdentificacionCliente identificacionCliente;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        identificacionMapperExt = new IdentificacionConverterDtoToPayload();
        identidadService = new IdentidadServiceImpl(identificacionCliente, identificacionMapperExt,jsonUtilities);
    }

    @Test
    void validarIdentidad() throws JSONException {
        Optional<ResponseDTO> result = identidadService.validarIdentidad(MockClienteDTORequest.getClientDTO());
        Assert.assertNotNull(result);
    }

}