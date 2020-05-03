package co.com.santander.core.services.command;

import co.com.santander.adapters.common.utilities.JsonUtilities;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.secondary.rest.OTPService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GenerarOTPCommandImpl implements Command {

    private final OTPService otpService;
    private final JsonUtilities jsonUtilities;

    @Autowired
    public GenerarOTPCommandImpl(OTPService otpService, JsonUtilities jsonUtilities) {
        this.otpService = otpService;
        this.jsonUtilities = jsonUtilities;
    }

    @Override
    public Optional<Map<String, Object>> callService(Object objects) throws JSONException {
        Map<String, Object> resultadoMap = new HashMap<>();
        Optional<ResponseDTO> result = otpService.generarOTP((DatosAdicionalesDTO) objects);
        if (result.isPresent()) {
            //Extraer respuestas
            String objectoGenerarCodigoOTPRespuesta = returnObjectJson("GenerarCodigoOTPRespuesta",
                    String.valueOf(result.get().getRespuestaServicio()), jsonUtilities);
            resultadoMap.put("codResultadoOTP", returnValueGetJson("ResultadoGeneracion", "codResultadoOTP", objectoGenerarCodigoOTPRespuesta, jsonUtilities));
            resultadoMap.put("idTransaccionOTP", returnValueGetJson("ResultadoGeneracion", "idTransaccionOTP", objectoGenerarCodigoOTPRespuesta, jsonUtilities));
        }
        return Optional.of(resultadoMap);
    }
}
