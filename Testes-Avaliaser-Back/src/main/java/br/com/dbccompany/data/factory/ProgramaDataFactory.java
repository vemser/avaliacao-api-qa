package br.com.dbccompany.data.factory;

import br.com.dbccompany.model.Programa;
import br.com.dbccompany.model.ProgramaBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgramaDataFactory {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static LocalDateTime now = LocalDateTime.now();
    static LocalDateTime future = LocalDateTime.now().plusHours(3);
    static String formatDateTimeNow = now.format(formatter);
    static String formatDateTimeFuture = future.format(formatter);



    public static Programa programaValido() {
        return novoPrograma();
    }


    private static Programa novoPrograma() {

        Programa programa = new ProgramaBuilder().
                nome("VemSer 10ed").
                situacao("ABERTO").
                descricao("Programa de formação profissional trilha Backend Vem Ser DBC 10º edição.").
                dataInicio(formatDateTimeNow).
                dataFim(formatDateTimeFuture).
                build();

        return programa;
    }
}
