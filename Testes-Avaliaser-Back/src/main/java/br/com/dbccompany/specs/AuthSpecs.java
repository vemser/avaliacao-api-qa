package br.com.dbccompany.specs;

import br.com.dbccompany.utils.Auth;
import br.com.dbccompany.data.changeless.Values;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AuthSpecs {

    private static String tokenAdmin = new Auth().autenticacaoAdmin();

    private AuthSpecs() {}

    public static RequestSpecification requestSpecAdmin() {
        return new RequestSpecBuilder().
                addHeader(Values.AUTHORIZATION, tokenAdmin).
                setContentType(ContentType.JSON).
                build();
    }

    public static RequestSpecification requestSpecFOTO() {
        return new RequestSpecBuilder().
                addHeader(Values.AUTHORIZATION, tokenAdmin).
                setContentType(ContentType.MULTIPART).
                build();
    }
}
