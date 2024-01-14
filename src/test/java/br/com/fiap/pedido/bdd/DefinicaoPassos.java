package br.com.fiap.pedido.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
public class DefinicaoPassos {

    private Response response;

    private String ENDPOINT_MENSAGENS = "http://localhost:8080/mensagens";


    @Quando("Quando requisitar a busca de produtos")
    public void requisitarBuscarMensagem() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/produtos", mensagemResponse.getId().toString());
    }

    @Então("a mensagem é exibida com sucesso")
    public void listaDeProdutosExibidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutosResponseSchema.json"));
    }
}
