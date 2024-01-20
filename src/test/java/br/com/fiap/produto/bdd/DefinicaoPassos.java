package br.com.fiap.produto.bdd;

import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.utils.ProdutosHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassos {

    private Response response;

    private String ENDPOINT_BASE = "http://localhost:8080/lanchonete";


    @Quando("submeter um novo produto")
    public ProdutoResponse submeterUmNovoProduto() {
        var produtoRequest = ProdutosHelper.gerarProdutoRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .when().post(ENDPOINT_BASE + "/produtos");
        return response.then().extract().as(ProdutoResponse.class);
    }
    @Então("o produto é registrado com sucesso")
    public void produtoRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutoResponseSchema.json"));
    }

    @Dado("que um produto já foi registrado")
    public void produtoJaFoiRegistrado() {
        submeterUmNovoProduto();
    }

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
                .get(ENDPOINT_BASE + "/produtos/{categoriaId}", 1);
    }

    @Então("a lista de produtos da categoria é exibida com sucesso")
    public void listaDeProdutosPorCategoriaExibidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutosResponseSchema.json"));
    }

    @Quando("requisitar a lista de categorias")
    public void requisitarListaDeCategorias() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_BASE + "/categorias");
    }
    @Então("a lista de categorias são exibidas com sucesso")
    public void listaDeCategoriasSaoExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CatetoriasResponseSchema.json"));
    }

    @Quando("requisitar a alteração do produto")
    public void requisitarAlteraçaoDoProduto() {
        var produtoRequest = ProdutosHelper.gerarProdutoRequestAlterado();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .when()
                .put("/mensagens/{id}", 1);
    }
    @Então("o produto é atualizado com sucesso")
    public void produtoAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutoResponseSchema.json"));
    }


    @Quando("requisitar a exclusão do produto")
    public void requisitarExclusaoDoProduto() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/mensagens/{id}", 1);
    }
    @Então("o produto é removido com sucesso")
    public void produtoRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


}
