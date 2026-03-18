package RT01_Aula1;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class T02 {
    @Test
     public void TesteT01(){
        Response respose = request(Method.GET,"https://restapi.wcaquino.me/ola");
        Assert.assertTrue(respose.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(respose.statusCode()==200);

        ValidatableResponse validacao = respose.then();
        validacao.statusCode(200);

    }

    @Test

    public  void TesteT02(){
        Response respose = request(Method.GET,"https://restapi.wcaquino.me/ola");
        ValidatableResponse validacao = respose.then();
        validacao.statusCode(200);

        get("https://restapi.wcaquino.me/ola").then().statusCode(200);

        given()
                .when()
                  .get("https://restapi.wcaquino.me/ola")
                        .then()
                .assertThat()
                            .statusCode(200);


    }

    @Test

    public void devoconhecerMatchersHamcret(){
        assertThat("Maria", Matchers.is("Maria"));


        List<Integer> impares = Arrays.asList(1,3,7,9);
        assertThat(impares, hasSize(4));
        assertThat(impares, contains(1,3,7,9));
        assertThat(impares, containsInAnyOrder(1,3,7,9));
        assertThat(impares, hasItem(1));//consegue verificar só um elemento

    }
    @Test
    public void devoValidarBody(){
        given()
                .when()
                    .get("https://restapi.wcaquino.me/ola")
                .then()
                .assertThat()
                .statusCode(200)
                .body(is("Ola Mundo!"))//restritiva"buscando pela mensagem de sucesso"
                .body(containsString("Mundo")) // pouco menos restritivo mas buscando pelo body
                .body(is(not(nullValue())))





        ;

    }

}
