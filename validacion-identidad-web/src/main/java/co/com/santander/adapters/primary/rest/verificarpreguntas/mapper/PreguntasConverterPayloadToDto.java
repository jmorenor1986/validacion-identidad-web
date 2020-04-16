package co.com.santander.adapters.primary.rest.verificarpreguntas.mapper;

import co.com.santander.adapters.common.mapper.ConverterRequestToDto;
import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.primary.rest.verificarpreguntas.input.VerificarPreguntasInput;
import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.RespuestaCuestionarioDTO;
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
