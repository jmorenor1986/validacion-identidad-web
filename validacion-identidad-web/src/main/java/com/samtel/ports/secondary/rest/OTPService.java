package com.samtel.ports.secondary.rest;

import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.DatosBasicosDTO;
import com.samtel.core.dto.ResponseDTO;

import java.util.Optional;

public interface OTPService {
    public Optional<ResponseDTO> generarOTP(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO);

    public Optional<ResponseDTO> iniciarTransaccion(DatosAdicionalesDTO datosAdicionalesDTO);
}
