package br.com.dbccompany.data.factory;

import br.com.dbccompany.model.Comentario;
import br.com.dbccompany.model.ComentarioBuilder;
import br.com.dbccompany.utils.Utils;

public class ComentarioDataFactory {

    public static Comentario criarComentario() {
        return novoComentario();
    }

    private static Comentario novoComentario() {

        Comentario comentario = new ComentarioBuilder()
                .comentario(Utils.faker.random().hex())
                .statusComentario(Utils.faker.number().numberBetween(1, 2))
                .build();

        return comentario;
    }
}
