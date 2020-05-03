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
public class ValidarOTPCommandImpl implements Command {

    private final OTPService otpService;
    private final JsonUtilities jsonUtilities;

    @Autowired
    public ValidarOTPCommandImpl(OTPService otpService, JsonUtilities jsonUtilities) {
        this.otpService = otpService;
        this.jsonUtilities = jsonUtilities;
    }

    @Override
    public Optional<Map<String, Object>> callService(Object objects) throws JSONException {
        Map<String, Object> result = new HashMap<>();
        Optional<ResponseDTO> resultado = otpService.verificarOTP((DatosAdicionalesDTO) objects);
        if (resultado.isPresent()) {
            result.put("codigoValido", returnValueGetJson("VerificarCodigoOTPRespuesta", "codigoValido",
                    resultado.get().getRespuestaServicio().toString(), jsonUtilities));
        }
        return Optional.of(result);
    }
}