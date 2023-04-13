package dataFactory;

import model.AcompanhamentoModel;
import model.ProgramaModel;
import model.TrilhaModel;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static dataFactory.DataFactory.dateFormat;
import static dataFactory.DataFactory.faker;

public class AcompanhamentoDataFactory {
//    region  CRIAR ACOMPANHAMENTO
    public static AcompanhamentoModel gerarAcompanhamentoValido(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdPrograma(idPrograma);
        acompanhamento.setTitulo("Trilha " + faker.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(faker.date().future(10, 5, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(faker.date().future(20, 11, TimeUnit.DAYS)));
        LocalTime horarioInicio = LocalTime.of(8, 0);
        LocalTime horarioFim = LocalTime.of(17, 0);
//        LocalTime horaAleatoria = horarioInicio.plusMinutes(faker.random().nextInt(540));
//        acompanhamento.setHorarioInicio(String.valueOf(horaAleatoria));
//        acompanhamento.setHorarioFim(String.valueOf(horaAleatoria.plusMinutes(30)));
        acompanhamento.setHorarioInicio(String.valueOf(horarioInicio));
        acompanhamento.setHorarioFim(String.valueOf(horarioFim));
        acompanhamento.setDuracao(30);
        acompanhamento.setNumeroResponsaveis(2);
        acompanhamento.setDescricao("Descrição da " + acompanhamento.getTitulo());
        acompanhamento.setStatus("ABERTO");

        return acompanhamento;
    }
    public static AcompanhamentoModel gerarAcompanhamentoValido(ProgramaModel programa) {
        return gerarAcompanhamentoValido(programa.getIdPrograma());
    }
    public static AcompanhamentoModel gerarAcompanhamentoSemId(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setTitulo("Trilha " + faker.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(faker.date().future(10, 5, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(faker.date().future(20, 11, TimeUnit.DAYS)));
        LocalTime horarioInicio = LocalTime.of(8, 0);
        LocalTime horarioFim = LocalTime.of(17, 0);
//        LocalTime horaAleatoria = horarioInicio.plusMinutes(faker.random().nextInt(540));
//        acompanhamento.setHorarioInicio(String.valueOf(horaAleatoria));
//        acompanhamento.setHorarioFim(String.valueOf(horaAleatoria.plusMinutes(30)));
        acompanhamento.setHorarioInicio(String.valueOf(horarioInicio));
        acompanhamento.setHorarioFim(String.valueOf(horarioFim));
        acompanhamento.setDuracao(30);
        acompanhamento.setNumeroResponsaveis(2);
        acompanhamento.setDescricao("Descrição da " + acompanhamento.getTitulo());
        acompanhamento.setStatus("ABERTO");

        return acompanhamento;
    }
    public static AcompanhamentoModel gerarAcompanhamentoSemId(ProgramaModel programa) {
        return gerarAcompanhamentoSemId(programa.getIdPrograma());
    }
//endregion
//region BUSCAR ACOMPANHAMENTO POR ID
    public static AcompanhamentoModel buscarAcompanhamentoPorId(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdPrograma(idPrograma);
        return acompanhamento;
    }
    public static AcompanhamentoModel buscarAcompanhamentoComIdNegativo(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdPrograma(00000000000000000);
        return acompanhamento;
    }
//endregion
//region BUSCAR ACOMPANHAMENTO POR ID DO ACOMPANHAMENTO
    public static AcompanhamentoModel buscarAcompanhamentoPorIdDoAcompanhamento(Integer idAcompanhamento) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdAcompanhamento(idAcompanhamento);
        return acompanhamento;
    }
    public static AcompanhamentoModel buscarAcompanhamentoComIdInvalido(Integer idAcompanhamento) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdAcompanhamento(000000000000000);
        return acompanhamento;
    }
//endregion
//region DELETAR ACOMPANHAMENTO
    public static AcompanhamentoModel deletarAcompanhamentocomIdErrado(Integer idAcompanhamento) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdAcompanhamento(0000000000000000000000000000000000000);
        return acompanhamento;
    }
//endregion
//region DESATIVAR AGENDAMENTO
    public static AcompanhamentoModel desativarAcompanhamentocomIdErrado() {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdAcompanhamento(0000000000000000000000000000000000000);
        return acompanhamento;
    }
//endregion

}
