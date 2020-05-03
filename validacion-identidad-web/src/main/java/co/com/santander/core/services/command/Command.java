package co.com.santander.core.services.command;

import co.com.santander.adapters.common.utilities.JsonUtilities;
import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
import org.json.JSONException;

import java.util.Map;
import java.util.Optional;

public interface Command {
    Optional<Map<String, Object>> callService(Object objects) throws JSONException;

    public default String returnValueGetJson(String nameObject, String nameKey, String json, JsonUtilities jsonUtilities) {
        Optional<String> respuestaValidacion = jsonUtilities.getValueForGivenKey(nameObject, nameKey, json);
        if (respuestaValidacion.isPresent())
            return respuestaValidacion.get();
        throw new BusinessException("Error al consultar la respuesta", new Throwable("Campo  no existe" + nameKey), "500");
    }

    public default String returnObjectJson(String nameObject, String json, JsonUtilities jsonUtilities) {
        Optional<String> respuestaObjecto = jsonUtilities.getObjectWithKey(nameObject, json);
        if (respuestaObjecto.isPresent())
            return respuestaObjecto.get();
        throw new BusinessException("Error al consultar la respuesta", new Throwable("Objecto  no existe" + nameObject), "500");
    }
}
