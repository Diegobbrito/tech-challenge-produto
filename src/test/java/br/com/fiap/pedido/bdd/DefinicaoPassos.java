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

    private String ENDPOINT_BASE = "http://localhost:8080/lanchonete";

    @Quando("requisitar a busca de produtos")
    public void requisitarBuscaDeProdutos() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_BASE + "/produtos");
    }

    @Então("a lista de todos produtos é exibida com sucesso")
    public void listaDeProdutosExibidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutosResponseSchema.json"));
    }

    @Quando("requisitar a busca de produtos de uma categoria")
    public void requisitarBuscaDeProdutosPorCategoria() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_BASE + "/produtos");
    }

    @Então("a lista de produtos da categoria é exibida com sucesso")
    public void listaDeProdutosPorCategoriaExibidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutosResponseSchema.json"));
    }

    @Quando("requisitar a lista de categorias")
    public void requisitar_a_lista_de_categorias() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("a lista de categorias são exibidas com sucesso")
    public void a_lista_de_categorias_são_exibidas_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CatetoriasResponseSchema.json"));
    }

    @Quando("requisitar a criação de um novo pedido")
    public void requisitar_a_criação_de_um_novo_pedido() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("um novo pedido para pagamento é realizado com sucesso")
    public void um_novo_pedido_para_pagamento_é_realizado_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
