package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.specs.AuthSpecs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UsuarioClient {

    public Response cadastrar(String tipoPerfil, String usuario) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(UsuarioData.TIPOPERFIL, tipoPerfil)
                    .body(usuario)
                .when()
                    .post(UsuarioData.CADASTRO_USUARIO)
                ;
    }

    public Response atualizarFoto(Integer idUsusario) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecFOTO())
                    .pathParam("idUsuario", idUsusario)
                    .multiPart(new File("./image/imageUpload.jpg"))
                .when()
                    .put(UsuarioData.UPLOAD_FOTO+"{idUsuario}")
                ;
    }

    public Response atualizar(Integer idUsusario, String usuarioAtualizado) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .pathParam("idUsuario", idUsusario)
                    .body(usuarioAtualizado)
                .when()
                    .put(UsuarioData.ATUALIZAR_USUARIO+"{idUsuario}")
                ;
    }

    public Response buscarFoto(Integer idUsuario) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .pathParam("idUsuario", idUsuario)
                .when()
                    .get(UsuarioData.BUSCAR_FOTO+"{idUsuario}")
                ;
    }
}
