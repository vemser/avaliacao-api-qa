package dataFactory;

import model.AcompanhamentoModel;
import model.AgendamentoModel;
import model.AvaliacaoModel;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;


public class AgendamentoDataFactory {
    private static Faker faker = new Faker();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//region CRIAR AGENDAMENTO
    public static AgendamentoModel gerarAgendamento(Integer idAvaliacao) {
        AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();
        AgendamentoModel agendamento = new AgendamentoModel();
        agendamento.setidAvaliacao(idAvaliacao);
        LocalTime horario = LocalTime.of(8, 0);
        String dia = "2023-05-20";
        LocalTime horaAleatoria = horario.plusMinutes(faker.random().nextInt(540));
        agendamento.setDataHorario(dia + "T" + horaAleatoria);
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
        agendamento.setidAvaliacao(745);
        LocalTime horario = LocalTime.of(8, 0);
        LocalTime horaAleatoria = horario.plusMinutes(faker.random().nextInt(540));
        agendamento.setDataHorario(dateFormat.format(faker.date().future(10, 8, TimeUnit.DAYS)) + "T" + horaAleatoria);
        agendamento.setResponsavel(faker.name().nameWithMiddle());
        agendamento.setIdAgendamento(idAgendamento);
        agendamento.setAtivo("true");
        agendamento.setIdAcompanhamento(724);
        return agendamento;
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
