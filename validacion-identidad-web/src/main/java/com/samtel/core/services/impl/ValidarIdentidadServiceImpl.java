package com.samtel.core.services.impl;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.core.services.ValidarIdentidadService;
import com.samtel.ports.secondary.rest.IdentidadService;
import com.samtel.ports.secondary.rest.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidarIdentidadServiceImpl implements ValidarIdentidadService {
    private final IdentidadService identidadService;
    private final OTPService otpService;

    @Autowired
    public ValidarIdentidadServiceImpl(IdentidadService identidadService, OTPService otpService) {
        this.identidadService = identidadService;
        this.otpService = otpService;
    }

    @Override
    public Optional<ResponseDTO> validar(ClienteDTO clienteDTO) {
        return Optional.empty();
    }
}
