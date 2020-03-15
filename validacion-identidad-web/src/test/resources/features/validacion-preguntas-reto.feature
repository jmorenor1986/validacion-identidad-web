Feature: Validación Preguntas Reto

    Scenario: Validacion preguntas reto correctas
        Given validacion preguntas reto son correctas con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | idCuestionario | regCuestionario | idPregunta | idRespuesta | codSapSolucion | nitEntidadExterna |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 00080036190    | 00000053       | 366105          | 1          | 03          |                |                   |
        When se invoca el servicio validacionRespuestas
        Then retorna codRespuesta "1"
        And status code 200

    Scenario: Validacion preguntas reto no correctas
        Given validacion preguntas reto no son correctas con los siguientes datos
        | codAliado | usuarioAliado               | sesionId                                 | ipOrigen  | numeroSolicitudCredito | tipoIdentificacion | identificacion | idCuestionario | regCuestionario | idPregunta | idRespuesta | codSapSolucion | nitEntidadExterna |
        | 001       | pepito.perez@sanitas.com.co | fb2e77d.47a0479900504cb3ab4a1f626d174d2d | 127.0.0.1 | 000012                 | 1                  | 00080036190    | 00000053       | 366105          | 1          | 03          |                |                   |
        When se invoca el servicio validacionRespuestas
        Then retorna codRespuesta "0"
        And status code 200