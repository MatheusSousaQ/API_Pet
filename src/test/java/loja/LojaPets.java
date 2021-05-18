package loja;

import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LojaPets extends  BasePets{

    @Test
    public void CriarOrdemDeCompraPet () throws IOException {
        String jsonBody = lerJson("dados/LojaPets/CriarOrdemDeCompraPet.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(enderecoRaizAPI + "/store/order")
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(19))
                .body("petId", is(1997))
                .body("quantity", is(1))
                .body("status", is("placed"));
    }
}
