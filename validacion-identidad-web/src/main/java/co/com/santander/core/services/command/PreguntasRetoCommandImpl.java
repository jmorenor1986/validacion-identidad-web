package co.com.santander.core.services.command;

import co.com.santander.adapters.common.utilities.JsonUtilities;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.secondary.rest.IdentidadService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PreguntasRetoCommandImpl implements Command {
    private final JsonUtilities jsonUtilities;
    private final IdentidadService identidadService;

    @Autowired
    public PreguntasRetoCommandImpl(JsonUtilities jsonUtilities, IdentidadService identidadService) {
        this.jsonUtilities = jsonUtilities;
        this.identidadService = identidadService;
    }

    @Override
    public Optional<Map<String, Object>> callService(Object objects) throws JSONException {
        Optional<ResponseDTO> resultadoInvocarPreguntas = identidadService.obtenerPreguntasReto((DatosAdicionalesDTO) objects);
        Map<String, Object> result = new HashMap<>();
        if (resultadoInvocarPreguntas.isPresent())
            result.put("Preguntas", resultadoInvocarPreguntas.get().getRespuestaServicio());
        return Optional.of(result);
    }
}
