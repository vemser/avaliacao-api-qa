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
    public static AcompanhamentoModel gerarAcompanhamentoValido(Integer idPrograma) {
        AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
        acompanhamento.setIdPrograma(idPrograma);
        acompanhamento.setTitulo("Trilha " + faker.name().firstName());
        acompanhamento.setDataInicio(dateFormat.format(faker.date().future(40, 5, TimeUnit.DAYS)));
        acompanhamento.setDataFim(acompanhamento.getDataInicio());
        LocalTime horarioInicio = LocalTime.of(8, 0);
        LocalTime horarioFim = LocalTime.of(17, 0);
//        LocalTime horaAleatoria = horarioInicio.plusMinutes(faker.random().nextInt(540));
//        acompanhamento.setHorarioInicio(String.valueOf(horaAleatoria));
//        acompanhamento.setHorarioFim(String.valueOf(horaAleatoria.plusMinutes(30)));
        acompanhamento.setHorarioInicio(String.valueOf(horarioInicio));
        acompanhamento.setHorarioFim(String.valueOf(horarioFim));
        acompanhamento.setDuracao(30);
        acompanhamento.setNumeroResponsaveis(faker.random().nextInt(5));
        acompanhamento.setDescricao("Descrição da " + acompanhamento.getTitulo());
        acompanhamento.setStatus("ABERTO");

        return acompanhamento;
    }
    public static AcompanhamentoModel gerarAcompanhamentoValido(ProgramaModel programa) {
        return gerarAcompanhamentoValido(programa.getIdPrograma());
    }

}
