package com.samtel.ports.secondary.rest;

import com.samtel.core.dto.ClienteDTO;

import java.util.Optional;

public interface OTPService {
    public Optional<String> generarOTP(ClienteDTO clienteDTO);
}
