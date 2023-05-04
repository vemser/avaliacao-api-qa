package dataFactory;

import model.AcompanhamentoModel;
import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;



public class AcompanhamentoDataFactory {
    private static Faker faker = new Faker();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    region  CRIAR ACOMPANHAMENTO
    public static AcompanhamentoModel gerarAcompanhamentoValido(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdPrograma(idPrograma);
        acompanhamento.setTitulo("Trilha " + faker.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(faker.date().future(10, 2, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(faker.date().future(20, 19, TimeUnit.DAYS)));
        LocalTime horarioInicio = LocalTime.of(8, 0);
        LocalTime horarioFim = LocalTime.of(17, 0);
        acompanhamento.setHorarioInicio(String.valueOf(horarioInicio));
        acompanhamento.setHorarioFim(String.valueOf(horarioFim));
        acompanhamento.setDuracao(30);
        acompanhamento.setNumeroResponsaveis(2);
        acompanhamento.setDescricao("Descrição da " + acompanhamento.getTitulo());
        acompanhamento.setStatus("ABERTO");
        return acompanhamento;
    }
    public static AcompanhamentoModel gerarAcompanhamentoSemId(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setTitulo("Trilha " + faker.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(faker.date().future(10, 1, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(faker.date().future(90, 30, TimeUnit.DAYS)));
        LocalTime horarioInicio = LocalTime.of(8, 0);
        LocalTime horarioFim = LocalTime.of(17, 0);
        acompanhamento.setHorarioInicio(String.valueOf(horarioInicio));
        acompanhamento.setHorarioFim(String.valueOf(horarioFim));
        acompanhamento.setDuracao(30);
        acompanhamento.setNumeroResponsaveis(2);
        acompanhamento.setDescricao("Descrição da " + acompanhamento.getTitulo());
        acompanhamento.setStatus("ABERTO");
        return acompanhamento;
    }
//endregion
//region ALTERAR ACOMPANHAMENTO
    public static AcompanhamentoModel alterarAcompanhamento(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdPrograma(idPrograma);
        acompanhamento.setTitulo("Trilha " + faker.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(faker.date().future(40, 39, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(faker.date().future(2, 1, TimeUnit.DAYS)));
        LocalTime horarioInicio = LocalTime.of(8, 0);
        LocalTime horarioFim = LocalTime.of(17, 0);
        acompanhamento.setHorarioInicio(String.valueOf(horarioInicio));
        acompanhamento.setHorarioFim(String.valueOf(horarioFim));
        acompanhamento.setDuracao(30);
        acompanhamento.setNumeroResponsaveis(2);
        acompanhamento.setDescricao("Descrição da " + acompanhamento.getTitulo());
        acompanhamento.setStatus("ABERTO");

        return acompanhamento;
    }
        public static AcompanhamentoModel alterarAcompanhamento(AcompanhamentoModel acompanhamento) {
            return alterarAcompanhamento(acompanhamento.getIdAcompanhamento());
    }
    public static AcompanhamentoModel alterarAcompanhamentoSemId(Integer acompanhamento) {
            return alterarAcompanhamento(0000000);
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
    public static AcompanhamentoModel deletarAcompanhamentoPorId(Integer acompanhamento) {
        AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();
        acompanhamentoModel.setIdAcompanhamento(acompanhamento);
        return acompanhamentoModel;
    }
    public static AcompanhamentoModel desativarAcompanhamentocomIdErrado(Integer id) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdAcompanhamento(0000000000000000000000000000000000000);
        return acompanhamento;
    }
//endregion

}
