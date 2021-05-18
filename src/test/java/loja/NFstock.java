package loja;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class NFstock {
    String enderecoRaizAPI = "https://petstore.swagger.io/v2";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void VerificarCustodia() throws IOException {
        String bodyJson = lerJson("dados/verificarCustodia.json");

        given()
                .log().all()
                .contentType("application/json")
                .body(bodyJson)
        .when()
                .post("http://nfstock-webservice-release.interno.pack.alterdata.com.br/ServicoAlterdata.svc/json/ClienteCustodia")
        .then()
                .log().all()
                .statusCode(200);


    }

    @Test
    public void BuscarNFSes() throws IOException {
        String bodyJson = lerJson("dados/verificarCustodia.json");

        given()
                .log().all()
                .auth().none()
        .when()
                .get("https://nfse-service-nfstock-dev.alterdatasoftware.com.br/api/nfse?data=2021-04-01&documento=33010836000126%20&nsu=0")
        .then()
                .log().all()
                .statusCode(200);
    }
}
