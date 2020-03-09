Feature: Validacion Identidad

    Scenario: Cliente viable pero se le permite reintentar
        Given un cliente con los siguientes datos
        | primerNombre | segundoNombre |
        | john         | moreno        |
        When Se invoca servicio validacion
        Then retorna codRespuesta "1"
        And status code "200"
