Feature: Validación Identidad

    Scenario: Cliente viable pero se le permite reintentar
        Given un cliente viable pero se le permite reintentar
        When Se invoca servicio validación
        Then retorna codRespuesta 1
        And status code 200

    Scenario: Cliente no viable
        Given un cliente no viable
        When Se invoca servicio validación
        Then retorna codRespuesta 0
        And status code 200

    Scenario: Cliente viable con OTP
        Given un cliente viable CON OTP
        When Se invoca servicio validación
        Then retorna codRespuesta 2
        And respuestaServicio not null
        And status code 200

    Scenario: Cliente viable pero con preguntas reto
        Given un cliente viable pero con preguntas reto
        When Se invoca cliente validación
        Then retorna codRespuesta 3
        And respuestaServicio not null
        And status code 200