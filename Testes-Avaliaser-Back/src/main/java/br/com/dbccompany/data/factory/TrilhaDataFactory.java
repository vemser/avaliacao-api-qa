package br.com.dbccompany.data.factory;


import br.com.dbccompany.model.Trilha;
import br.com.dbccompany.model.TrilhaBuilder;
import br.com.dbccompany.utils.Utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

import java.util.Date;

public class TrilhaDataFactory {

    public static Trilha novaTrilha() {
        return createTrilha();
    }

    public static Trilha vincularTrilha(Integer idTrilha, String loginUsuario) {
        return createVinculacao(idTrilha, loginUsuario);
    }

    public static Trilha retirarEdicaoDaTrilha(Trilha trilha) {

        trilha.setAnoEdicao(StringUtils.EMPTY);
        return trilha;
    }

    private static Trilha createVinculacao(Integer idTrilha, String loginUsuario) {
        Trilha trilhaVinculada = new TrilhaBuilder()
                .idTrilha(idTrilha)
                .login(loginUsuario)
                .buildVincular();

        return trilhaVinculada;
    }

    private static Trilha createTrilha() {

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

        Trilha trilha = new TrilhaBuilder().
                nome(Utils.faker.name().firstName()).
                edicao(Utils.faker.number().numberBetween(1, 100)).
                anoEdicao(data.format(new Date())).
                build();

        return trilha;
    }
}
