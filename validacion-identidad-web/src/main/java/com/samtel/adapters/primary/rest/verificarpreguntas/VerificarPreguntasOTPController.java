package com.samtel.adapters.primary.rest.verificarpreguntas;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.primary.rest.dto.Response;
import com.samtel.adapters.primary.rest.verificarpreguntas.input.VerificarPreguntasInput;
import com.samtel.adapters.primary.rest.verificarpreguntas.mapper.PreguntasConverterPayloadToDto;
import com.samtel.ports.primary.VerificarPreguntasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Response> verificarPreguntas(GeneralPayload<VerificarPreguntasInput> verificarPreguntas) {
        Response response = modelMapper.map(verificarPreguntasService
                .verificarPreguntas(preguntasConverterPayloadToDto.requestToDto(verificarPreguntas)), Response.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
