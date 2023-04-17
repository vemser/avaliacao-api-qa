package dataFactory;

import model.ProgramaModel;
import java.util.concurrent.TimeUnit;

public class ProgramaDataFactory extends DataFactory {
//region GERAR PROGRAMA
    public static ProgramaModel gerarProgramaValido() {
        ProgramaModel programa = new ProgramaModel();
        programa.setNome("Programa VemSer 13");
        programa.setDescricao("Descrição do " + programa.getNome());
        programa.setDataInicio(dateFormat.format(faker.date().future(2, 1, TimeUnit.DAYS)));
        programa.setDataFim(dateFormat.format(faker.date().future(40, 39, TimeUnit.DAYS)));
        programa.setStatus("FECHADO");
        return programa;
    }

    public static ProgramaModel gerarProgramaComDataAbaixoDaAtual() {
        ProgramaModel programa = gerarProgramaValido();
        programa.setDataInicio(dateFormat.format(faker.date().past(10, 1, TimeUnit.DAYS)));
        programa.setDataFim(dateFormat.format(faker.date().past(90, 30, TimeUnit.DAYS)));
        return programa;
    }
//endregion
}
