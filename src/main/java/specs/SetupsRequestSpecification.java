package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.Autenticacao;

public class SetupsRequestSpecification {
    public static RequestSpecification requestSpecification(){
        return new RequestSpecBuilder()
                .addHeader("Authorization", Autenticacao.token())
                .setContentType(ContentType.JSON)
                .build()
                ;
    }
}
