package dataFactory;

import model.ProgramaModel;
import model.TrilhaModel;

public class TrilhaDataFactory extends DataFactory{
    public static TrilhaModel gerarTrilhaValida(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdTrilha(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        return trilha;
    }
    public static TrilhaModel gerarTrilhaValida(ProgramaModel programa) {
        return gerarTrilhaValida(programa.getIdPrograma());
    }
}
