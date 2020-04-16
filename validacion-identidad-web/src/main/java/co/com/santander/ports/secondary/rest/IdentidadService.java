package co.com.santander.ports.secondary.rest;

import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import org.json.JSONException;

import java.util.Optional;

public interface IdentidadService {

    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO) throws JSONException;

    public Optional<ResponseDTO> obtenerPreguntasReto(DatosAdicionalesDTO datosAdicionalesDTO);

    public Optional<ResponseDTO> validarPreguntasReto(CuestionarioDTO cuestionarioDTO);
}
