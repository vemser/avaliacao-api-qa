package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.Autenticacao;

public class ImageRequestSpec {
    public static RequestSpecification requestSpecification(){

        return new RequestSpecBuilder()
                .addHeader("Authorization", Autenticacao.token())
                .build()
                ;
    }
}
