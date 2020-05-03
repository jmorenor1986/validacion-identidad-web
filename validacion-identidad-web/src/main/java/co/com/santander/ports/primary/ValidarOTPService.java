package co.com.santander.ports.primary;

import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import org.json.JSONException;

import java.util.Optional;

public interface ValidarOTPService {
    Optional<ResponseDTO> validarOTP(DatosAdicionalesDTO datosAdicionalesDTO) throws JSONException;
}
