package co.com.santander.core.services.impl;

import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
import co.com.santander.core.dto.ClienteDTO;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.services.command.GenerarOTPCommandImpl;
import co.com.santander.core.services.command.IniciarOTPCommandImpl;
import co.com.santander.core.services.command.PreguntasRetoCommandImpl;
import co.com.santander.core.services.command.ValidarIdentidadCommandImpl;
import co.com.santander.ports.primary.ValidarIdentidadService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ValidarIdentidadServiceImpl implements ValidarIdentidadService {

    private final ValidarIdentidadCommandImpl validarIdentidadCommand;
    private final IniciarOTPCommandImpl iniciarOTPCommand;
    private final PreguntasRetoCommandImpl preguntasRetoCommand;
    private final GenerarOTPCommandImpl generarOTPCommand;

    @Autowired
    public ValidarIdentidadServiceImpl(ValidarIdentidadCommandImpl validarIdentidadCommand, IniciarOTPCommandImpl iniciarOTPCommand,
                                       PreguntasRetoCommandImpl preguntasRetoCommand, GenerarOTPCommandImpl generarOTPCommand) {
        this.validarIdentidadCommand = validarIdentidadCommand;
        this.iniciarOTPCommand = iniciarOTPCommand;
        this.preguntasRetoCommand = preguntasRetoCommand;
        this.generarOTPCommand = generarOTPCommand;
    }

    @Override
    public Optional<ResponseDTO> validar(ClienteDTO clienteDTO) throws JSONException {
        DatosAdicionalesDTO datosAdicionalesDTO = DatosAdicionalesDTO.builder()
                .datosBasicosDTO(clienteDTO.getDatosBasicosDTO())
                .numeroCelular(clienteDTO.getNumeroCelular())
                .build();
        //Invocar cliente validacion con sus variables
        Optional<Map<String, Object>> resultadoValidacionIdentidad = validarIdentidadCommand.callService(clienteDTO);
        if (resultadoValidacionIdentidad.isPresent())
            if ("01".equalsIgnoreCase(String.valueOf(resultadoValidacionIdentidad.get().get("resultado"))) || "05".equalsIgnoreCase(String.valueOf(resultadoValidacionIdentidad.get().get("resultado")))) {
                datosAdicionalesDTO.setRegValidacion((String) resultadoValidacionIdentidad.get().get("regValidacion"));
                Optional<Map<String, Object>> resultadoIniciarTransaccion = iniciarOTPCommand.callService(datosAdicionalesDTO);
                if (resultadoIniciarTransaccion.isPresent()) {
                    if ("99".equalsIgnoreCase((String) resultadoIniciarTransaccion.get().get("codResultadoOTP"))) {
                        datosAdicionalesDTO.setCodigoOTP((String) resultadoIniciarTransaccion.get().get("idTransaccionOTP"));
                        return invocaPreguntasReto(datosAdicionalesDTO);
                    } else {
                        datosAdicionalesDTO.setIdTransaccionOTP((String) resultadoIniciarTransaccion.get().get("idTransaccionOTP"));
                        Optional<Map<String, Object>> resultadoGenerarOTP = generarOTPCommand.callService(datosAdicionalesDTO);
                        if (resultadoGenerarOTP.isPresent()) {
                            if ("99".equalsIgnoreCase((String) resultadoGenerarOTP.get().get("codResultadoOTP"))) {
                                datosAdicionalesDTO.setCodigoOTP((String) resultadoGenerarOTP.get().get("idTransaccionOTP"));
                                return invocaPreguntasReto(datosAdicionalesDTO);
                            } else {
                                return setResponse("{idTransaccionOTP:" + resultadoGenerarOTP.get().get("idTransaccionOTP").toString().concat(",regValidacion:").concat(datosAdicionalesDTO.getRegValidacion()),
                                        "2", null);
                            }
                        }
                    }
                }
            }
        if ("09".equalsIgnoreCase(String.valueOf(resultadoValidacionIdentidad.get().get("resultado")))) {
            return setResponse("Cliente no viable", "0", null);
        } else {
            return setResponse("Cliente no viable, se le permite reintentar", "1", null);
        }
    }

    private Optional<ResponseDTO> setResponse(String respuestaServicio, String codRespuesta, String mensajeError) {
        return Optional.of(ResponseDTO.builder()
                .codRespuesta(codRespuesta)
                .respuestaServicio(respuestaServicio)
                .mensajeError(mensajeError)
                .build());
    }


    private Optional<ResponseDTO> invocaPreguntasReto(DatosAdicionalesDTO datosAdicionalesDTO) throws JSONException {
        Optional<Map<String, Object>> resultadoInvocarPreguntasReto = preguntasRetoCommand.callService(datosAdicionalesDTO);
        if (resultadoInvocarPreguntasReto.isPresent()) {
            return setResponse((String) resultadoInvocarPreguntasReto.get().get("Preguntas"), "3", null);
        }
        throw new BusinessException("Error al invocar preguntas reto", new Throwable("Datos no encontrados"), "500");
    }
}
