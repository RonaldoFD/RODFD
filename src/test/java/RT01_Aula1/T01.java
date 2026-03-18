package RT01_Aula1;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

public class T01 {
    static void main() {
            Response respose = RestAssured.request(Method.GET,"https://restapi.wcaquino.me/ola");
            System.out.println(respose.getBody().asString().equals("Ola Mundo!"));
            System.out.println(respose.statusCode()==200);

        ValidatableResponse validacao = respose.then();
        validacao.statusCode(201);
    }

}
