package com.samtel.ports.primary;

import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.ResponseDTO;

import java.util.Optional;

public interface ValidarOTPService {
    Optional<ResponseDTO> validarOTP(DatosAdicionalesDTO datosAdicionalesDTO);
}
