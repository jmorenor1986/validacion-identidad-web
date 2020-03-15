Feature: Validacion Identidad

    Scenario: Cliente viable pero se le permite reintentar
        Given un cliente con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | primerNombre | segundoNombre | primerApellido | segundoApellido | fechaExpedicion |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 00080036190    | Freddy       |               | Forero         |                 | 986947200120    |
        When Se invoca servicio validacion
        Then retorna codRespuesta "1"
        And status code "200"

    Scenario: Cliente viable con OTP
        Given un cliente con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | primerNombre | segundoNombre | primerApellido | segundoApellido | fechaExpedicion |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 00080036190    | Freddy       |               | Forero         |                 | 986947200120    |
        When Se invoca servicio validacion
        Then retorna codRespuesta "2"
        And respuestaServicio "idTransaccionOTP"
        And status code "200"

    Scenario: Cliente viable pero con preguntas reto
        Given un cliente con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | primerNombre | segundoNombre | primerApellido | segundoApellido | fechaExpedicion |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 00080036190    | Freddy       |               | Forero         |                 | 986947200120    |
        When Se invoca servicio validacion
        Then retorna codRespuesta "3"
        And respuestaServicio "not null"
        And status code "200"

    Scenario: Cliente no viable
        Given un cliente con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | primerNombre | segundoNombre | primerApellido | segundoApellido | fechaExpedicion |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 00080036190    | Freddy       |               | Forero         |                 | 986947200120    |
        When Se invoca servicio validacion
        Then retorna codRespuesta "0"
        And status code "200"