package dataFactory;

import model.AcompanhamentoModel;
import model.AgendamentoModel;
import model.AvaliacaoModel;
import net.datafaker.Faker;
import utils.FakerHolder;
import utils.Gerador;
import utils.Validator;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;


public class AgendamentoDataFactory {
    //region CRIAR AGENDAMENTO
    public static AgendamentoModel gerarAgendamento(Integer idAvaliacao, LocalDateTime dataHorario) {
        AgendamentoModel agendamento = new AgendamentoModel();

        String dataAleatoria = Gerador.gerarDataAleatoriaDentroDePeriodoDeAcompanhamento(dataHorario);

        agendamento.setIdAvaliacao(idAvaliacao);
        agendamento.setIdAgendamento(FakerHolder.instance.random().nextInt(1000));
        agendamento.setDataHorario(dataAleatoria);
        agendamento.setResponsavel(FakerHolder.instance.name().nameWithMiddle());
        agendamento.setAtivo(true);

        return agendamento;
    }

    public static AgendamentoModel gerarAgendamento(AvaliacaoModel avaliacao, LocalDateTime dataHorario) {
        return gerarAgendamento(avaliacao.getIdAvaliacao(), dataHorario);
    }

    public static AgendamentoModel gerarAgendamentoComIdErrado() {
        return gerarAgendamento(0, LocalDateTime.now());
    }

    //endregion
//region ATUALIZAR AGENDAMENTO
    public static AgendamentoModel atualizarAgendamento(AgendamentoModel agendamentoModel, Integer idAvaliacao, LocalDateTime dataHorario) {
        AgendamentoModel agendamento = new AgendamentoModel();

        String dataAleatoria = Gerador.gerarDataAleatoriaDentroDePeriodoDeAcompanhamento(dataHorario);

        agendamento.setIdAvaliacao(idAvaliacao);
        agendamento.setDataHorario(dataAleatoria);
        agendamento.setIdAgendamento(agendamentoModel.getIdAgendamento());
        agendamento.setResponsavel(agendamentoModel.getResponsavel());
        return agendamento;
    }

    ////endregion
////region DELETAR AGENDAMENTO
    public static AgendamentoModel deletarAgendamento(Integer agendamento) {
        AgendamentoModel agendamentoModel = new AgendamentoModel();

        agendamentoModel.setIdAgendamento(agendamento);
        return agendamentoModel;
    }

    public static AgendamentoModel deletarAgendamentoComIdErrado() {
        AgendamentoModel agendamentoModel = new AgendamentoModel();

        agendamentoModel.setIdAgendamento(0);
        return agendamentoModel;
    }
//endregion
}
