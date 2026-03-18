package RT01_Aula1;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.*;

public class T03_Json {
    @Test
    public void deveFerificarPrimeiro(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/1")
                .then()
               .statusCode(200)
               .body("id", is(1)) // Buscando por ID
               .body("name", containsString("João da Silva"))// Verificando se o nome tem Silva
               .body("age", greaterThan(18)) // Verificando se a idade é maior de 18
        ;
    }
    @Test
    public void deveVerificarOtrasFormas(){
        Response response = request(Method.GET, "https://restapi.wcaquino.me/users/1");
        // path

        Assert.assertEquals(new Integer(1),response.path("id"));
        Assert.assertEquals(new Integer(1),response.path("%s", "id"));

        // Jsonpath

        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1, jpath.getInt("id"));

        //From

        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1,id);


    }

    @Test
    public void deveVerificarSegundoNivel(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/2")
                .then()
                .statusCode(200)
                .body("name", containsString("Maria Joaquina"))// Verificando se o nome tem Silva
                .body("endereco.rua", is("Rua dos bobos")) // Verificando se a idade é maior de 18
        ;

    }

    @Test
    public void DeveVerificarLista(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/3")
                .then()
                .statusCode(200)
                .body("name", containsString("Ana"))// Verificando se o nome tem Silva
                .body("filhos", hasSize(2)) // Verificando se a idade é mai
                .body("filhos[0].name", is("Zezinho"))
                .body("filhos[1].name,",is("Luizinho"))
        ;

    }
    @Test
    public void deveRetornarErroUsuarioInexistente(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/4")
                .then()
                .statusCode(404)
                .body("error", is("Usuário inexistente"))
        ;

    }
    @Test
    public void deveVerificarListaNaRaiz(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("name", hasItems("João da Silva", "Maria Joaquina","Ana Maria"))
                .body("age[1]", is(25))


        ;

    }
}
