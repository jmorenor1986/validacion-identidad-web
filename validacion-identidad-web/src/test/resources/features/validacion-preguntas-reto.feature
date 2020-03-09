Feature: Validación Preguntas Reto

    Scenario: Respuestas correctas
        Given las respuestas son correctas
        When se invoca el servicio validacionRespuestas
        Then retorna codRespuesta 1
        And status code 200

    Scenario: Respuestas no correctas
        Given las respuestas no son correctas
        When se invoca el servicio validacionRespuestas
        Then retorna codRespuesta 0
        And status code 200