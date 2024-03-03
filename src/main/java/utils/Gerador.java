package utils;

import service.AusenciaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class Gerador {
    private static final Random RANDOM = new Random();

    public static String gerarDataAleatoriaDentroDePeriodoDeAcompanhamento(LocalDateTime dataHorario) {
        LocalDateTime data = LocalDateTime.of(dataHorario.getYear(), dataHorario.getMonth(), dataHorario.getDayOfMonth(), dataHorario.getHour(), dataHorario.getMinute())
                .plusDays(RANDOM.nextInt(21))
                .plusHours(RANDOM.nextInt(5))
                .plusMinutes(RANDOM.nextInt(60));

        data = Validator.validarSeDataCaiNoFimDeSemana(data);

        return data.toString();
    }

    public static String dataComHorario(Integer idAusencia) {
        String dataComHorario = AusenciaService.buscarAusenciaPorId(idAusencia).extract().path("diaDeFalta");
        return LocalDate.parse(dataComHorario).toString();
    }
}
