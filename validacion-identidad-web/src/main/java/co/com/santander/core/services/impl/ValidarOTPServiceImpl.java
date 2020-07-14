package co.com.santander.core.services.impl;

import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.services.command.PreguntasRetoCommandImpl;
import co.com.santander.core.services.command.ValidarOTPCommandImpl;
import co.com.santander.ports.primary.ValidarOTPService;
import com.google.common.hash.Hashing;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class ValidarOTPServiceImpl implements ValidarOTPService {
    private final ValidarOTPCommandImpl validarOTPCommand;
    private final PreguntasRetoCommandImpl preguntasRetoCommand;

    @Autowired
    public ValidarOTPServiceImpl(ValidarOTPCommandImpl validarOTPCommand, PreguntasRetoCommandImpl preguntasRetoCommand) {
        this.validarOTPCommand = validarOTPCommand;
        this.preguntasRetoCommand = preguntasRetoCommand;
    }

    @Override
    public Optional<ResponseDTO> validarOTP(DatosAdicionalesDTO datosAdicionalesDTO) throws JSONException {
        //invocar cliente validarOTP
        datosAdicionalesDTO.setCodigoOTP(Hashing.sha256().hashString(datosAdicionalesDTO.getCodigoOTP(), StandardCharsets.UTF_8).toString());
        Optional<Map<String, Object>> resultadoClienteVerificarOTP = validarOTPCommand.callService(datosAdicionalesDTO);

        if (resultadoClienteVerificarOTP.isPresent()) {
            if (resultadoClienteVerificarOTP.get().get("codigoValido").toString().equalsIgnoreCase("false")) {
                Optional<Map<String, Object>> resultadoPreguntasReto = preguntasRetoCommand.callService(datosAdicionalesDTO);
                return setResponseDTO(resultadoPreguntasReto.get().get("Preguntas"), "2");
            } else {
                return setResponseDTO(datosAdicionalesDTO.getIdTransaccionOTP(), "1");
            }
        }
        throw new BusinessException("Error de datos", new Throwable("Error al consultar los datos"), "1");
    }

    private Optional<ResponseDTO> setResponseDTO(Object response, String codResult) {
        return Optional.of(ResponseDTO.builder()
                .codRespuesta(codResult)
                .respuestaServicio(response)
                .build());
    }
}
