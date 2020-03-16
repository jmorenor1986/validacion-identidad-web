package com.samtel.integration.steps;

import com.samtel.adapters.primary.rest.dto.Response;
import com.samtel.adapters.primary.rest.identidad.payload.ClienteInput;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ValidacionIdentidadStep extends ContextLoader {

    private ClienteInput clientePayload;
    private ResponseEntity<?> response;
    @Autowired
    protected HttpClient httpClient;


    @Given("^un cliente con los siguientes datos$")
    public void clienteConDatos(DataTable payload) {
        List<Map<String, String>> rows = payload.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            clientePayload = ClienteInput.builder()
                    .primerNombre(columns.get("primerNombre"))
                    .segundoNombre(columns.get("segundoNombre"))
                    .build();
        }

    }

    @When("Se invoca servicio validacion")
    public void invocaServicioValidacion() {
        response = httpClient.postWithRequest("/v1/validacion/identidad/", this.clientePayload);
    }

    @Then("retorna codRespuesta {string}")
    public void retornaCodRespuesta(String codRespuesta) {
        Response body = (Response) response.getBody();
        Assert.assertEquals(codRespuesta, body.getCodRespuesta());
    }

    @And("status code {string}")
    public void statusCode(String statusCode) {
        Assert.assertEquals("" + response.getStatusCode().value(), statusCode);
    }

}
