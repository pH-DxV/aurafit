package br.unitins.topicos1.aurafit.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.aurafit.model.Fornecedor;
import br.unitins.topicos1.aurafit.service.FornecedorService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FornecedorResourceTest {

    @InjectMock
    FornecedorService service;

    @Test
    void testListar() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazaoSocial("Fornecedor Teste");

        when(service.listarTodos()).thenReturn(List.of(fornecedor));

        given()
          .when().get("/fornecedores")
          .then()
             .statusCode(200)
             .body("[0].razaoSocial", equalTo("Fornecedor Teste"));
    }
}