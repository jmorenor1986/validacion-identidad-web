package co.com.santander.adapters.secondary.rest.identidad;

import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
import co.com.santander.core.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MockClienteDTORequest {
    public static ClienteDTO getClientDTO() {
        return ClienteDTO.builder()
                .datosBasicosDTO(DatosBasicosDTO.builder()
                        .codAliado("1234")
                        .identificacion("12344")
                        .ipOrigen("123456")
                        .numeroSolicitudCredito("1234")
                        .sesionId("12345")
                        .tipoIdentificacion("1234t")
                        .usuarioAliado("123456")
                        .build())
                .fechaExpedicion("234567")
                .numeroCelular("234567")
                .primerApellido("1234567")
                .primerNombre("234567")
                .segundoApellido("1234567")
                .segundoNombre("123456")
                .build();
    }

    public static ResponseEntity<?> getResponse() {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .mensajeError("null")
                .codRespuesta("1")
                .respuestaServicio("{\"RespValidacion\":{\"Nombre\":\"FORERO PARRA FREDDY\",\"alertas\":false,\"consultasDisponibles\":1,\"Identificacion\":{\"tipo\":1,\"numero\":\"00080036190\"},\"resultado\":\"01\",\"requiereOTP\":true,\"codigoAlerta\":\"00\",\"valNombre\":true,\"resultadoProceso\":true,\"valFechaExp\":true,\"regValidacion\":5964360,\"respuestaAlerta\":\"04\",\"valApellido\":true,\"excluirCliente\":false,\"FechaExpedicion\":{\"timestamp\":986947200000}}}")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
