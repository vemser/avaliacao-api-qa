package dataFactory;

import model.AcompanhamentoModel;
import utils.FakerHolder;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class AcompanhamentoDataFactory {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //    region  CRIAR ACOMPANHAMENTO
    public static AcompanhamentoModel gerarAcompanhamentoValido(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdPrograma(idPrograma);
        acompanhamento.setTitulo("Trilha " + FakerHolder.instance.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(FakerHolder.instance.date().future(10, 2, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(FakerHolder.instance.date().future(20, 19, TimeUnit.DAYS)));
        return getAcompanhamentoModel(acompanhamento);
    }

    public static AcompanhamentoModel gerarAcompanhamentoSemId() {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setTitulo("Trilha " + FakerHolder.instance.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(FakerHolder.instance.date().future(10, 1, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(FakerHolder.instance.date().future(90, 30, TimeUnit.DAYS)));
        return getAcompanhamentoModel(acompanhamento);
    }

    //endregion
//region ALTERAR ACOMPANHAMENTO
    public static AcompanhamentoModel alterarAcompanhamento(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdPrograma(idPrograma);
        acompanhamento.setTitulo("Trilha " + FakerHolder.instance.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(FakerHolder.instance.date().future(40, 39, TimeUnit.DAYS)));
        acompanhamento.setDataFim(dateFormat.format(FakerHolder.instance.date().future(2, 1, TimeUnit.DAYS)));
        return getAcompanhamentoModel(acompanhamento);
    }

    public static AcompanhamentoModel alterarAcompanhamento(AcompanhamentoModel acompanhamento) {
        return alterarAcompanhamento(acompanhamento.getIdAcompanhamento());
    }

    public static AcompanhamentoModel alterarAcompanhamentoSemId() {
        return alterarAcompanhamento(0);
    }

    //endregion
//region BUSCAR ACOMPANHAMENTO POR ID
    public static AcompanhamentoModel buscarAcompanhamentoPorId(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdPrograma(idPrograma);
        return acompanhamento;
    }

    public static AcompanhamentoModel buscarAcompanhamentoComIdNegativo() {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdPrograma(0);
        return acompanhamento;
    }

    //endregion
//region BUSCAR ACOMPANHAMENTO POR ID DO ACOMPANHAMENTO
    public static AcompanhamentoModel buscarAcompanhamentoPorIdDoAcompanhamento(Integer idAcompanhamento) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdAcompanhamento(idAcompanhamento);
        return acompanhamento;
    }

    public static AcompanhamentoModel buscarAcompanhamentoComIdInvalido() {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdAcompanhamento(0);
        return acompanhamento;
    }

    //endregion
//region DELETAR ACOMPANHAMENTO
    public static AcompanhamentoModel deletarAcompanhamentocomIdErrado(Integer idAcompanhamento) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdAcompanhamento(0);
        return acompanhamento;
    }

    //endregion
//region DESATIVAR AGENDAMENTO
    public static AcompanhamentoModel deletarAcompanhamentoPorId(Integer acompanhamento) {
        AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();

        acompanhamentoModel.setIdAcompanhamento(acompanhamento);
        return acompanhamentoModel;
    }

    public static AcompanhamentoModel desativarAcompanhamentocomIdErrado() {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();

        acompanhamento.setIdAcompanhamento(0);
        return acompanhamento;
    }
//endregion

    private static AcompanhamentoModel getAcompanhamentoModel(AcompanhamentoModel acompanhamento) {
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
}
