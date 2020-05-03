package co.com.santander.core.services.command;

import co.com.santander.adapters.common.utilities.JsonUtilities;
import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.secondary.rest.IdentidadService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class ValidarIdentidadCommandImpl implements Command {

    private final IdentidadService identidadService;
    private final JsonUtilities jsonUtilities;

    @Autowired
    public ValidarIdentidadCommandImpl(IdentidadService identidadService, JsonUtilities jsonUtilities) {
        this.identidadService = identidadService;
        this.jsonUtilities = jsonUtilities;
    }


    @Override
    public Optional<Map<String, Object>> callService(Object object) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        Optional<ResponseDTO> responseValidacion = identidadService.validarIdentidad((ClienteDTO) object);
        //Extraer respuestas
        result.put("resultado", returnValueGetJson("RespValidacion", "resultado", responseValidacion.get().getRespuestaServicio().toString(), jsonUtilities));
        result.put("regValidacion", returnValueGetJson("RespValidacion", "regValidacion", responseValidacion.get().getRespuestaServicio().toString(), jsonUtilities));
        return Optional.of(result);
    }


}
