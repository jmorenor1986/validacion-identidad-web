Feature: Validacion OTP

    Scenario: La OTP está correcta
        Given La OTP está correcta
        When Se invoca el servicio validacionOTP
        Then retorna codRespuesta 1
        And status code 200

    Scenario: La OTP no está correcta y se requieren preguntas reto
        Given La OTP no está correcta
        And se requieren preguntas reto
        When Se invoca el servicio validacionOTP
        Then retorna codRespuesta 2
        And status code 200