package dataFactory;

import com.sun.jdi.IntegerValue;
import model.ProgramaModel;
import model.TrilhaModel;

import static java.lang.Integer.valueOf;

public class TrilhaDataFactory extends DataFactory{

//region    CENÁRIOS DE CRIAÇÃO DE TRILHA

    public static TrilhaModel gerarTrilhaValida(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdPrograma(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        return trilha;
    }
    public static TrilhaModel gerarTrilhaValida(ProgramaModel programa) {
        return gerarTrilhaValida(programa.getIdPrograma());
    }

    public static TrilhaModel gerarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        return trilha;
    }
    public static TrilhaModel gerarTrilhaComLetrasNoId(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdTrilha(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("Não sei");
        return trilha;
    }
//endregion
//region    CENÁRIOS DE ATUALIZAÇÃO DE TRILHA
    public static TrilhaModel atualizarTrilhaValida(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdPrograma(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("ABERTO");
        return trilha;
    }
    public static TrilhaModel atualizarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("ABERTO");
        return trilha;
    }
    public static TrilhaModel atualizarTrilhSemCorpo() {
        TrilhaModel trilha = new TrilhaModel();
        return trilha;
    }
//endregion


}
