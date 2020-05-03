package co.com.santander.adapters.primary.rest.identidad;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.primary.rest.common.dto.Response;
import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
import co.com.santander.adapters.primary.rest.identidad.input.ClienteInput;
import co.com.santander.adapters.primary.rest.identidad.mapper.IdentificacionConverterPayloadToDto;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.primary.ValidarIdentidadService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/identidad")
public class ValidarIdentidadController {

    private final ValidarIdentidadService validarIdentidadService;
    private final IdentificacionConverterPayloadToDto identificacionConverterPayloadToDto;
    private final ModelMapper modelMapper;

    @Autowired
    public ValidarIdentidadController(ValidarIdentidadService validarIdentidadService, IdentificacionConverterPayloadToDto identificacionConverterPayloadToDto,
                                      ModelMapper modelMapper) {
        this.validarIdentidadService = validarIdentidadService;
        this.identificacionConverterPayloadToDto = identificacionConverterPayloadToDto;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public ResponseEntity<Response> validadIdentidad(@RequestBody GeneralPayload<ClienteInput> clientePayLoad) throws JSONException {
        Optional<ResponseDTO> result = validarIdentidadService.validar(identificacionConverterPayloadToDto.requestToDto(clientePayLoad));
        if (result.isPresent())
            return new ResponseEntity<>(modelMapper.map(result.get(), Response.class), HttpStatus.OK);
        throw new BusinessException("Error al consultar los datos", new Throwable("valores no retornados"), "0");
    }
}
