package co.com.santander.ports.secondary.rest;

import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;

import java.util.Optional;

public interface OTPService {
    public Optional<ResponseDTO> generarOTP(DatosAdicionalesDTO datosAdicionalesDTO);

    public Optional<ResponseDTO> iniciarTransaccion(DatosAdicionalesDTO datosAdicionalesDTO);

    public Optional<ResponseDTO> verificarOTP(DatosAdicionalesDTO datosAdicionalesDTO);
}
