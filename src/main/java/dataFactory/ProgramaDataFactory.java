package dataFactory;

import model.ProgramaModel;
import net.datafaker.Faker;
import service.ProgramaService;
import utils.FakerHolder;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProgramaDataFactory{
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//region GERAR PROGRAMA
    public static ProgramaModel gerarProgramaValido() {
        return getProgramaModel();
    }

    public static ProgramaModel gerarProgramaComNomeNulo() {
        ProgramaModel programa = new ProgramaModel();

        programa.setNome("");
        return getProgramaModel(programa);
    }
//endregion
//region ID PARA CENARIOS DE ERRO
    public static ProgramaModel programaComValorDeIdNegativo(Integer idPrograma){
        ProgramaModel programa = new ProgramaModel();

        programa.setIdPrograma(idPrograma);
        return programa;
    }
    public static ProgramaModel programaComIdDeValorZero(){
        ProgramaModel programa = new ProgramaModel();

        programa.setIdPrograma(0);
        return programa;
    }
//endregion
//region ATUALIZAR PROGRAMA
    private static ProgramaModel getProgramaModel(ProgramaModel programa) {
        programa.setDescricao("Descrição do " + programa.getNome());
        programa.setDataInicio(dateFormat.format(FakerHolder.instance.date().future(2, 1, TimeUnit.DAYS)));
        programa.setDataFim(dateFormat.format(FakerHolder.instance.date().future(40, 39, TimeUnit.DAYS)));
        programa.setStatus("FECHADO");
        return programa;
    }

    public static ProgramaModel atualizarProgramaComValorDeIdVazio(){
        ProgramaModel programa = new ProgramaModel();

        programa.setIdPrograma(-45678);
        return programa;
    }
//endregion

    private static ProgramaModel atualizarPrograma(){
        return getProgramaModel();
    }

    private static ProgramaModel getProgramaModel() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(10000) + 1;
        ProgramaModel programa = new ProgramaModel();

        programa.setNome("Programa VemSer " + numeroAleatorio);
        return getProgramaModel(programa);
    }
}
