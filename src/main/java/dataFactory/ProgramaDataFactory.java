package dataFactory;

import model.ProgramaModel;
import java.util.concurrent.TimeUnit;

public class ProgramaDataFactory extends DataFactory {
    public static ProgramaModel gerarProgramaValido() {
        ProgramaModel programa = new ProgramaModel();
        programa.setNome("Programa " + faker.name().firstName());
        programa.setDescricao("Descrição do " + programa.getNome());
        programa.setDataInicio(dateFormat.format(faker.date().future(10, 1, TimeUnit.DAYS)));
        programa.setDataFim(dateFormat.format(faker.date().future(90, 30, TimeUnit.DAYS)));
        programa.setStatus("ABERTO");
        return programa;
    }

}
