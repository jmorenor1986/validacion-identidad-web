package co.com.santander.ports.primary;

import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.ResponseDTO;
import org.json.JSONException;

import java.util.Optional;

public interface ValidarIdentidadService {

    Optional<ResponseDTO> validar(ClienteDTO clienteDTO) throws JSONException;

}
