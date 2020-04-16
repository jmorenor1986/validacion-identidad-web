package co.com.santander.adapters.secondary.rest.identidad.mapper;

import co.com.santander.adapters.common.mapper.ConverterDtoToRequest;
import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.secondary.rest.common.payload.SolucionPayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.RespuestaPayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.ValidarPreguntasPayload;
import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.RespuestaCuestionarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuestionarioConverterDtoToPayload extends ConverterDtoToRequest<ValidarPreguntasPayload, CuestionarioDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public CuestionarioConverterDtoToPayload(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GeneralPayload<ValidarPreguntasPayload> dtoToRequest(CuestionarioDTO request) {
        GeneralPayload<ValidarPreguntasPayload> payload = new GeneralPayload<>();
        payload.setRequestHeader(setHeader(request.getDatosBasicosDTO()));
        payload.setRequestBody(ValidarPreguntasPayload.builder()
                .idCuestionario(request.getIdCuestionario())
                .regCuestionario(request.getRegCuestionario())
                .respuesta(setRespuesta(request.getRespuesta()))
                .solucion(SolucionPayload.builder()
                        .codSapSolucion("")
                        .nitEntidadExterna("")
                        .build())
                .build());
        return payload;
    }

    private RespuestaPayload setRespuesta(RespuestaCuestionarioDTO respuestaCuestionarioDTO) {
        return modelMapper.map(respuestaCuestionarioDTO, RespuestaPayload.class);
    }


}
