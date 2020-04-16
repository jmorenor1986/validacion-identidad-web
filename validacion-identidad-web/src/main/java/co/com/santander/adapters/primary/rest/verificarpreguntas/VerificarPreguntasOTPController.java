package co.com.santander.adapters.primary.rest.verificarpreguntas;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.primary.rest.dto.Response;
import co.com.santander.adapters.primary.rest.verificarpreguntas.input.VerificarPreguntasInput;
import co.com.santander.adapters.primary.rest.verificarpreguntas.mapper.PreguntasConverterPayloadToDto;
import co.com.santander.ports.primary.VerificarPreguntasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/preguntas")
public class VerificarPreguntasOTPController {

    private final PreguntasConverterPayloadToDto preguntasConverterPayloadToDto;
    private final ModelMapper modelMapper;
    private final VerificarPreguntasService verificarPreguntasService;

    @Autowired
    public VerificarPreguntasOTPController(PreguntasConverterPayloadToDto preguntasConverterPayloadToDto, ModelMapper modelMapper, VerificarPreguntasService verificarPreguntasService) {
        this.preguntasConverterPayloadToDto = preguntasConverterPayloadToDto;
        this.modelMapper = modelMapper;
        this.verificarPreguntasService = verificarPreguntasService;
    }

    @PostMapping("/")
    public ResponseEntity<Response> verificarPreguntas(@RequestBody GeneralPayload<VerificarPreguntasInput> verificarPreguntas) {
        Response response = modelMapper.map(verificarPreguntasService
                .verificarPreguntas(preguntasConverterPayloadToDto.requestToDto(verificarPreguntas)), Response.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
