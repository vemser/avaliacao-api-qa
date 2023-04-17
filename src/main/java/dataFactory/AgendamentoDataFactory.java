package dataFactory;

import model.AcompanhamentoModel;
import model.AgendamentoModel;
import model.AvaliacaoModel;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static dataFactory.DataFactory.dateFormat;
import static dataFactory.DataFactory.faker;

public class AgendamentoDataFactory {
//region CRIAR AGENDAMENTO
    public static AgendamentoModel gerarAgendamento(Integer idAvaliacao) {
        AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();
        AgendamentoModel agendamento = new AgendamentoModel();
        agendamento.setidAvaliacao(idAvaliacao);
        LocalTime horario = LocalTime.of(8, 0);
        LocalTime horaAleatoria = horario.plusMinutes(faker.random().nextInt(540));
        agendamento.setDataHorario(dateFormat.format(faker.date().future(10, 8, TimeUnit.DAYS)) + "T" + horaAleatoria);
        agendamento.setResponsavel(faker.name().nameWithMiddle());

        return agendamento;
    }
    public static AgendamentoModel gerarAgendamento(AvaliacaoModel avaliacao) {
        return gerarAgendamento(avaliacao.getIdAvaliacao());
    }
    public static AgendamentoModel gerarAgendamentoComIdErrado(Integer agendar) {
        return gerarAgendamento(00000000000000000);
    }
//endregion
//region ATUALIZAR AGENDAMENTO
    public static AgendamentoModel atualizarAgendamento(Integer idAgendamento) {
        AgendamentoModel agendamento = new AgendamentoModel();
        agendamento.setidAvaliacao(495);
        LocalTime horario = LocalTime.of(8, 0);
        LocalTime horaAleatoria = horario.plusMinutes(faker.random().nextInt(540));
        agendamento.setDataHorario(dateFormat.format(faker.date().future(10, 8, TimeUnit.DAYS)) + "T" + horaAleatoria);
        agendamento.setResponsavel(faker.name().nameWithMiddle());
        agendamento.setIdAgendamento(idAgendamento);
        agendamento.setAtivo("true");
        return agendamento;
    }
    public static AcompanhamentoModel informarId(Integer agendamento) {
        AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();
        acompanhamentoModel.setIdAcompanhamento(agendamento);
        return acompanhamentoModel;
    }
//endregion
//region DELETAR AGENDAMENTO
    public static AgendamentoModel deletarAgendamento(Integer agendamento) {
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        agendamentoModel.setIdAgendamento(agendamento);
        return agendamentoModel;
    }
    public static AgendamentoModel deletarAgendamentoComIdErrado(Integer agendamento) {
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        agendamentoModel.setIdAgendamento(000000000000000000000000);
        return agendamentoModel;
    }
//endregion
}
