package com.samtel.ports.primary;

import com.samtel.core.dto.CuestionarioDTO;
import com.samtel.core.dto.ResponseDTO;

import java.util.Optional;

public interface VerificarPreguntasService {

    Optional<ResponseDTO> verificarPreguntas(CuestionarioDTO cuestionarioDTO);
}
