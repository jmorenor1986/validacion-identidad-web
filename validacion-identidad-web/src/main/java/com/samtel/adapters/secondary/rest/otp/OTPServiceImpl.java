package com.samtel.adapters.secondary.rest.otp;

import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.DatosBasicosDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.ports.secondary.rest.OTPService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OTPServiceImpl implements OTPService {
    @Override
    public Optional<ResponseDTO> generarOTP(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ResponseDTO> iniciarTransaccion(DatosBasicosDTO datosBasicosDTO, String regValidacion) {
        return Optional.empty();
    }
}
