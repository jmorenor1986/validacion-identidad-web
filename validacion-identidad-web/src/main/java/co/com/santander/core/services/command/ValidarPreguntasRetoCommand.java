package co.com.santander.core.services.command;

import co.com.santander.adapters.common.utilities.JsonUtilities;
import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.secondary.rest.IdentidadService;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ValidarPreguntasRetoCommand implements Command {
    private final JsonUtilities jsonUtilities;
    private final IdentidadService identidadService;

    public ValidarPreguntasRetoCommand(JsonUtilities jsonUtilities, IdentidadService identidadService) {
        this.jsonUtilities = jsonUtilities;
        this.identidadService = identidadService;
    }

    @Override
    public Optional<Map<String, Object>> callService(Object objects) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        Optional<ResponseDTO> resultService = identidadService.validarPreguntasReto((CuestionarioDTO) objects);
        if (resultService.isPresent()) {
            result.put("aprobacion", returnValueGetJson("Evaluacion", "aprobacion", resultService.get().getRespuestaServicio().toString(), jsonUtilities));
        }
        return Optional.of(result);
    }
}
