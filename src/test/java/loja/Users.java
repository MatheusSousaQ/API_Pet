package loja;

import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class Users extends BasePets{

    @Test
    public void executarUsersEmOrdem() throws IOException {
        CriarUsuario();
        ConsultarUsuario();
        AlterarUsuario();
        ExcluirUsuario();
    }

    @Test
    public void loginUser() throws IOException {
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

        System.out.println(token);
    }

    @Test
    public void CriarUsuario() throws IOException {
        String jsonBody = lerJson("dados/Users/CriarUsuario.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(enderecoRaizAPI + "/user")
        .then()
                .log().all()
                .statusCode(200)
                .body("message", is("18"))
                .body("code",is(200))
                ;
    }

    @Test
    public void ConsultarUsuario(){
        String username = "MatheusTeste";

        given()
                .log().all()
        .when()
                .get(enderecoRaizAPI + "/user/" + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("username", is(username))
                .body("id", is(18))
                .body("email", is("matheusteste@teste.com"))
                .body("phone", is("21 3333-4444"))
                .body("userStatus", is(0));
    }

    @Test
    public void AlterarUsuario() throws IOException {
        String username = "MatheusTeste";

        String jsonbody = lerJson("dados/Users/EditarUsuario.json");

        given()
                .log().all()
                .contentType("application/json")
                .body(jsonbody)
        .when()
                .put(enderecoRaizAPI + "/user/" + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", is("18"))
                .body("code", is(200));
    }

    @Test
    public void ExcluirUsuario() throws IOException {
        String username = "MatheusTeste";

        given()
                .log().all()
                .contentType("application/json")
        .when()
                .delete(enderecoRaizAPI + "/user/" + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", is("MatheusTeste"))
                .body("code", is(200));
    }
}
