package br.unitins.topicos1.aurafit.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.aurafit.model.Categoria;
import br.unitins.topicos1.aurafit.service.CategoriaService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CategoriaResourceTest {

    @InjectMock
    CategoriaService service;

    @Test
    void testListarTodas() {
        Categoria categoria = new Categoria();
        categoria.setNome("Whey");
        categoria.setDescricao("Teste");

        when(service.listarTodas()).thenReturn(List.of(categoria));

        given()
          .when().get("/categorias")
          .then()
             .statusCode(200)
             .body("[0].nome", equalTo("Whey"));
    }
}