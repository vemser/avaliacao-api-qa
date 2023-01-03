package br.com.dbccompany.data.factory;

import br.com.dbccompany.model.Trilha;
import br.com.dbccompany.model.TrilhaBuilder;
import br.com.dbccompany.utils.Utils;
import org.apache.commons.lang3.StringUtils;

public class TrilhaDataFactory {

    public static Trilha trilhaValida() { return novaTrilha();}

    public static Trilha trilhaComNomeVazio() {

        Trilha trilhaNomeVazio = novaTrilha();
        trilhaNomeVazio.setNome(StringUtils.EMPTY);

        return trilhaNomeVazio;
    }

    public static Trilha trilhaDescricaoVazio() {

        Trilha trilhaNomeVazio = novaTrilha();
        trilhaNomeVazio.setDescricao(StringUtils.EMPTY);

        return trilhaNomeVazio;
    }

    private static Trilha novaTrilha() {

        Trilha trilhaNova = new TrilhaBuilder()
                .nome(Utils.faker.computer().platform())
                .descricao("Nova Trilha")
                .build();

        return trilhaNova;
    }
}