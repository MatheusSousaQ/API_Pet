package loja;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class BasePets {
    public String token;

    String enderecoRaizAPI = "https://petstore.swagger.io/v2";

    public String extrairToken(){
        String user = "matheus";
        String password = "teste";

        String messageToken =
                given()
                        .contentType("application/json")
                        .log().all()
                        .when()
                        .get(enderecoRaizAPI + "/user/login?username=" + user + "&password=" + "teste")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("code", is(200))
                        .body("message", containsString("logged in user session:"))
                        .extract()
                        .path("message");
        token = messageToken.substring(24);
        return token;
    }

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
}
