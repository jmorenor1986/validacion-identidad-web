package co.com.santander.core.services.impl;

import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.services.command.ValidarPreguntasRetoCommand;
import co.com.santander.ports.primary.VerificarPreguntasService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class VerificarPreguntasServiceImpl implements VerificarPreguntasService {

    private final ValidarPreguntasRetoCommand validarPreguntasRetoCommand;

    @Autowired
    public VerificarPreguntasServiceImpl(ValidarPreguntasRetoCommand validarPreguntasRetoCommand) {
        this.validarPreguntasRetoCommand = validarPreguntasRetoCommand;
    }

    @Override
    public Optional<ResponseDTO> verificarPreguntas(CuestionarioDTO cuestionarioDTO) throws JSONException {
        Optional<Map<String, Object>> verificarPreguntasReto = validarPreguntasRetoCommand.callService(cuestionarioDTO);
        if (verificarPreguntasReto.isPresent()) {
            if (verificarPreguntasReto.get().get("aprobacion").equals("true")) {
                return setResponseDTO("Respuestas correctas", "1");
            }
        }
        return setResponseDTO("Respuestas Incorrectas", "0");

    }

    private Optional<ResponseDTO> setResponseDTO(Object response, String codResult) {
        return Optional.of(ResponseDTO.builder()
                .codRespuesta(codResult)
                .respuestaServicio(response)
                .build());
    }
}
