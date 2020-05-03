package co.com.santander.ports.primary;

import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.ResponseDTO;
import org.json.JSONException;

import java.util.Optional;

public interface VerificarPreguntasService {

    Optional<ResponseDTO> verificarPreguntas(CuestionarioDTO cuestionarioDTO) throws JSONException;
}
