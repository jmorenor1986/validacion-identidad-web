package com.samtel.adapters.primary.rest.identidad.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePayLoad {
    private String primerNombre;
    private String segundoNombre;
}
