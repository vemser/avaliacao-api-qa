package br.com.dbccompany.data.factory;

import br.com.dbccompany.dto.TrilhaDTO;
import br.com.dbccompany.model.*;
import br.com.dbccompany.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class AtividadeDataFactory {

    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static LocalDateTime future = LocalDateTime.now().plusHours(1);
    static String formatDateTimeNow = now.format(formatter);
    static String formatDateTimeFuture = future.format(formatter);

    public static Atividade novaAtividade(Integer idModulo, TrilhaDTO trilha) {
        return criarAtividade(idModulo, trilha);
    }

    private static Atividade criarAtividade(Integer idModulo, TrilhaDTO trilha) {

        Atividade novaAtividade = new AtividadeBuilder()
                .titulo("Backend")
                .instrucoes(Utils.faker.industrySegments().industry())
                .pesoAtividade(Utils.faker.number().numberBetween(1, 10))
                .dataCriacao(formatDateTimeNow)
                .dataEntrega(formatDateTimeFuture)
                .trilha(List.of(trilha))
                .idModulo(idModulo)
                .build();

        return novaAtividade;
    }
}
