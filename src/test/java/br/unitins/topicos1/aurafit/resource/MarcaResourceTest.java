package br.unitins.topicos1.aurafit.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.aurafit.model.Marca;
import br.unitins.topicos1.aurafit.service.MarcaService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MarcaResourceTest {

    @InjectMock
    MarcaService service;

    @Test
    void testListar() {
        Marca marca = new Marca();
        marca.setNome("DUX");

        when(service.listarTodas()).thenReturn(List.of(marca));

        given()
          .when().get("/marcas")
          .then()
             .statusCode(200)
             .body("[0].nome", equalTo("DUX"));
    }
}