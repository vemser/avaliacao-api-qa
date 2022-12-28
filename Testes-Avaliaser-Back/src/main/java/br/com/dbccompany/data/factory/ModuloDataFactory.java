package br.com.dbccompany.data.factory;

import br.com.dbccompany.model.*;
import br.com.dbccompany.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

import java.util.Date;


public class ModuloDataFactory {

    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static LocalDateTime future = LocalDateTime.now().plusHours(3);
    static String formatDateTimeNow = now.format(formatter);
    static String formatDateTimeFuture = future.format(formatter);

    public static Modulo novoModulo() {
        return criarModulo();
    }

    public static Modulo moduloDataFormatoErrado() {
        Date date = new Date();
        Modulo moduloDataFormatoErrado = criarModulo();
        moduloDataFormatoErrado.setDataInicio(DateUtils.addHours(date, 5).toString());
        moduloDataFormatoErrado.setDataFim(DateUtils.addHours(date, 7).toString());

        return moduloDataFormatoErrado;
    }

    public static Modulo moduloSemNome() {

        Modulo moduloSemNome = criarModulo();
        moduloSemNome.setNome(StringUtils.EMPTY);

        return moduloSemNome;
    }

    public static Modulo vincularModuloComTrilha(Integer idModulo, Integer idTrilha) {

        Modulo moduloVinculadoATrilha = new ModuloBuilder()
                .idMoulo(idModulo)
                .idTrilha(idTrilha)
                .buildVinculacaoComTrilha();

        return moduloVinculadoATrilha;
    }

    private static Modulo criarModulo() {

        Modulo modulo = new ModuloBuilder().
                nome(Utils.faker.name().firstName()).
                dataInicio(formatDateTimeNow).
                dataFim(formatDateTimeFuture).
                build();

        return modulo;
    }
}
