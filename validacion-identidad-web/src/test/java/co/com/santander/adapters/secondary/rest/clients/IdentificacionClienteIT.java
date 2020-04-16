package co.com.santander.adapters.secondary.rest.clients;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentificacionClienteIT {

    @Autowired
    private IdentificacionCliente identificacionCliente;

    //@Test
    //public void testValidarIdentificacion() {
    //    ResponseEntity<?> result = identificacionCliente.validacionIdentidad(MockRequest.clientePayload());
    //    Assert.assertEquals(200, result.getStatusCodeValue());
    //}
}
