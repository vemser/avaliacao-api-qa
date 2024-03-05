package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.Autenticacao;

public class SetupsRequestSpecification {

    public static RequestSpecification requestSpecification(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluLnRlc3RlIiwianRpIjoiMyIsImNhcmdvcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTcwOTI0NDE5NiwiZXhwIjoxNzExODM2MTk1fQ.hxbzlmPD0f02dte5cd07RHy6m8lVN6NrWENhtImq-NE";

        return new RequestSpecBuilder()
                .addHeader("Authorization", Autenticacao.token())
                .setContentType(ContentType.JSON)
                .build()
                ;
    }
}
