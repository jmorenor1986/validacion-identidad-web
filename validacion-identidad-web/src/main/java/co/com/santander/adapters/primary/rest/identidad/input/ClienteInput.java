package co.com.santander.adapters.primary.rest.identidad.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteInput {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaExpedicion;
    private String numeroCelular;
}
