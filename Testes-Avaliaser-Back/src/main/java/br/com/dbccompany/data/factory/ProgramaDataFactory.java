package br.com.dbccompany.data.factory;

import br.com.dbccompany.dto.ProgramaDTO;
import br.com.dbccompany.model.Programa;
import br.com.dbccompany.model.ProgramaBuilder;
import br.com.dbccompany.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgramaDataFactory {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static LocalDateTime now = LocalDateTime.now();
    static LocalDateTime future = LocalDateTime.now().plusDays(1);
    static String formatDateTimeNow = now.format(formatter);
    static String formatDateTimeFuture = future.format(formatter);



    public static Programa programaValido() {
        return novoPrograma();
    }

    public static Programa programaDataInicioMaiorQueDataFim() {
        Programa programaValido = novoPrograma();

        programaValido.setDataInicio(now.plusDays(2).format(formatter));
        return programaValido;
    }

    public static Programa modificarProgramaDataInicioMesMaiorQueDoze(Programa programa) {

        programa.setDataInicio("2022-13-29");
        return programa;
    }

    public static Programa programaDataInicioNull() {
        Programa programaValido = novoPrograma();

        programaValido.setDataInicio(StringUtils.EMPTY);
        return programaValido;
    }

    public static Programa programaDataFimNull() {
        Programa programaValido = novoPrograma();

        programaValido.setDataFim(StringUtils.EMPTY);
        return programaValido;
    }

    public static Programa programaNomeNull() {
        Programa programaValido = novoPrograma();

        programaValido.setNome(StringUtils.EMPTY);
        return programaValido;
    }

    public static Programa modificarNomePrograma(Programa programa, String nome) {

        programa.setNome(nome);
        return programa;
    }

    public static Programa modificarSituacaoPrograma(Programa programa, String situacao) {

        programa.setSituacao(situacao);
        return programa;
    }

    public static Programa modificarDescricaoPrograma(Programa programa, String descricao) {

        programa.setDescricao(descricao);
        return programa;
    }

    public static Programa modificarDataInicioPrograma(Programa programa, Integer adicaoDias) {

        programa.setDataInicio(now.plusDays(adicaoDias).format(formatter));
        return programa;
    }

    public static Programa modificarDataFimPrograma(Programa programa, Integer adicaoDias) {

        programa.setDataFim(now.plusDays(adicaoDias).format(formatter));
        return programa;
    }

    private static Programa novoPrograma() {

        String computerPlataform = Utils.faker.computer().operatingSystem();

        Programa programa = new ProgramaBuilder().
                nome("VemSer " + computerPlataform).
                situacao("ABERTO").
                descricao("Programa de formação profissional trilha " + computerPlataform + " Vem Ser DBC 10º edição.").
                dataInicio(formatDateTimeNow).
                dataFim(formatDateTimeFuture).
                build();

        return programa;
    }
}
