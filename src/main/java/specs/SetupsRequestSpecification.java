package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.Autenticacao;
import utils.ConfigProperties;

public class SetupsRequestSpecification {

    public static RequestSpecification requestSpecification(){
        ConfigProperties.initializePropertyFile();

        String token = ConfigProperties.properties.getProperty("admin_token");

        return new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setContentType(ContentType.JSON)
                .build()
                ;
    }
}
