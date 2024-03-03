package dataFactory;

import model.AusenciaModel;
import org.apache.commons.lang3.StringUtils;
import utils.Gerador;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AusenciaDataFactory {

    final static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static AusenciaModel cadastrarAusencia() {
        AusenciaModel ausencia = new AusenciaModel();
        ausencia.observacao = "Fui ao médico";
        ausencia.diaDeFalta = LocalDate.now().plusDays(1).toString();
        ausencia.horarioInicio = LocalTime.now().format(format);
        ausencia.horarioFim = LocalTime.now().plusHours(6).format(format);
        ausencia.idEstagiario = 1;
        ausencia.link = StringUtils.EMPTY;

        return ausencia;
    }

    public static File gerarImagem() {
        return new File("C:/Users/grego/OneDrive/Imagens/image.png");
    }
}
