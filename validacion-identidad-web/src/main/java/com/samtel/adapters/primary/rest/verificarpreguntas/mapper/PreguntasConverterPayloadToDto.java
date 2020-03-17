package com.samtel.adapters.primary.rest.verificarpreguntas.mapper;

import com.samtel.adapters.common.mapper.ConverterRequestToDto;
import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.primary.rest.verificarpreguntas.input.VerificarPreguntasInput;
import com.samtel.core.dto.CuestionarioDTO;
import com.samtel.core.dto.RespuestaCuestionarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreguntasConverterPayloadToDto extends ConverterRequestToDto<VerificarPreguntasInput, CuestionarioDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public PreguntasConverterPayloadToDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CuestionarioDTO requestToDto(GeneralPayload<VerificarPreguntasInput> payload) {
        return CuestionarioDTO.builder()
                .datosBasicosDTO(setDatosBasicos(payload.getRequestHeader()))
                .idCuestionario(payload.getRequestBody().getIdCuestionario())
                .regCuestionario(payload.getRequestBody().getRegCuestionario())
                .respuesta(modelMapper.map(payload.getRequestBody().getRespuesta(), RespuestaCuestionarioDTO.class))
                .build();
    }
}
