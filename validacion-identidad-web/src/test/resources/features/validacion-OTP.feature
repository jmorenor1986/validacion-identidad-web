Feature: Validacion OTP

    Scenario: Validacion OTP correcta
        Given Validación OTP con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | regValidacion | codigoOTP                                                        | idTransaccionOTP                     |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 80852789       | 5733042       | F82304227C5F268515691F80AEB51E20A2DBE7565B7271ECB57BDB9951698295 | 46cddf56-6efa-4e45-88e1-9ccf6531f18b |
        When Se invoca el servicio validacionOTP
        Then retorna codRespuesta "1"
        And respuestaServicio "idTransaccionOTP"
        And status code 200

    Scenario: La OTP no está correcta y se requieren preguntas reto
        Given Validación OTP con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | regValidacion | codigoOTP                                                        | idTransaccionOTP                     |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 80852789       | 5733042       | F82304227C5F268515691F80AEB51E20A2DBE7565B7271ECB57BDB9951698295 | 46cddf56-6efa-4e45-88e1-9ccf6531f18b |
        When Se invoca el servicio validacionOTP
        Then retorna codRespuesta "2"
        And respuestaServicio "not null"
        And status code 200