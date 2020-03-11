package com.samtel.core.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaExpedicion;
    private String celular;
    private String tipoDocumento;
    private String numeroDocumento;
    private RequestHeaderDTO requestHeaderDTO;
}