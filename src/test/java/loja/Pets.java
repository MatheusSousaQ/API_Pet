package loja;

import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class Pets extends BasePets {

    @Test
    public void rodarEmOrdem() throws IOException {
        IncluirPet();
        consultaPetPorId();
        editaPet();
        //deletePet();
    }

    @Test
    public  void IncluirPet() throws IOException {
        String jsonBody = lerJson("dados/Pets/incluirPet.json");

        given()                                            //Dado que
                .contentType("application/json")           //conteudo do corpo da requisicao
                .log().all()                               //gerar log completo
                .body(jsonBody)                            //conteudo do corpo da requisicao
        .when()                                            //quando
                .post(enderecoRaizAPI + "/pet")       //tipo e endereco da requisicao
        .then()                                            //entao
                .log().all()                               //gerar log completo
                .statusCode(200)                           //validar status code da resposta da requisicao
                .body("id", is(1997))           //valida se o id tem o conteudo esperado
                .body("name", is("miau"))
                .body("tags.name", contains("adocao"))
        ;
    }


    @Test
    public void consultaPetPorId(){
        String petId = "1997";
        given()
                .log().all()
        .when()
                .get(enderecoRaizAPI + "/pet/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(1997))
                .body("name", is("miau"))
                .body("category.name", is("gato"));

    }

    @Test
    public void editaPet() throws IOException {
        String jsonBody = lerJson("dados/Pets/EditaPet.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(enderecoRaizAPI + "/pet")
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(1997))
                .body("name", is("miau"))
                .body("category.name", is("pokemon"))
                .body("status", is("pokemon gold"));
    }

    @Test
    public void deletePet(){
        String petId = "1997";
        given()
                .log().all()
        .when()
                .delete(enderecoRaizAPI + "/pet/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", is("1997"));

    }
}
